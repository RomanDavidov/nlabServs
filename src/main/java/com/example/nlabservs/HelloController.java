package com.example.nlabservs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    private static final ObservableList<String> companiesData = FXCollections.observableArrayList();
    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Справочники обновлены!");
        initData();
    }



    private void initData() throws SQLException {
        var conn = Connection_class.getInstance().getConnection();
        CallableStatement cs = conn.prepareCall("call solution_med.nlab_srvdep_ext(?);");
        cs.setString(1,companies.getValue());
        cs.executeQuery();
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
    }
}
