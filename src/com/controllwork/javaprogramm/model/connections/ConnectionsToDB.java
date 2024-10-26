package com.controllwork.javaprogramm.model.connections;

import java.sql.Connection;

public abstract class ConnectionsToDB {
    static String HOST = "";
    static String USERNAME = "";
    static String PASSWORD = "";

    private Connection connection;

    public ConnectionsToDB(String HOST, String USERNAME, String PASSWORD) {
        this.HOST = HOST;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }


    public Connection getConnection() {
        return connection;
    }
}
