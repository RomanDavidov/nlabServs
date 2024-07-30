package com.example.nlabservs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_class {
    private static Connection_class instance = null;
    public static Connection_class getInstance() throws SQLException {
        if(null == instance)
            instance = new Connection_class();

        return instance;
    }
    public Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://10.0.0.71:5432/RD9","postgres","postgres");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
