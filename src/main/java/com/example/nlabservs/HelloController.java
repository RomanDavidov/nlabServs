package com.example.nlabservs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    ComboBox<String> companies = new ComboBox<>();
    @FXML
    ButtonBar buttonBar = new ButtonBar();

    Button buttonDownloadDictionaries = new Button("Загрузить справочники");
    Button buttonUpdateDictionaries = new Button("Обновить справочники");
    Button buttonDeleteDictionaries = new Button("Удалить справочники");

    private static final ObservableList<String> companiesData = FXCollections.observableArrayList();
    private void initData() throws SQLException {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("ОШИБКА!!!");
        errorAlert.setContentText("НУЖНО ВЫБРАТЬ КАМПАНИЮ!!!!!!!");
        var conn = Connection_class.getInstance().getConnection();
        CallableStatement cs = conn.prepareCall("call nlab_srvdep_ext(?);");
        cs.setString(1,companies.getValue());
        if(companies.getValue() == null){
            errorAlert.showAndWait();
        }
        if(companies.getValue() != null){
            welcomeText.setText("Справочники загружены!");
            cs.executeQuery();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var companiesStream = DictionaresDAO.getInstance().findAll();
        for(String compani : companiesStream){
            companiesData.add(
                    compani
            );
        }
        companies.setPromptText("Выберете нужную кампанию");
        companies.setItems(companiesData);
        buttonBar.getButtons().addAll(buttonDownloadDictionaries,buttonUpdateDictionaries,buttonDeleteDictionaries);
        buttonBar.setPadding(new Insets(10));
        buttonDownloadDictionaries.setOnAction(event ->{
            try {
                initData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        buttonUpdateDictionaries.setOnAction(event -> {
            welcomeText.setText("Справочники обновлены!");
        });
        buttonDeleteDictionaries.setOnAction(event -> {
            welcomeText.setText("Справочники удалены!");
        });
    }
}
