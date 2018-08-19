package pl.coderslab.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    // plik sluzacy do polaczenia z baza danych
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Workshop_2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    private static DbManager instance;
    private Connection connection;

    private DbManager() {
    }

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}