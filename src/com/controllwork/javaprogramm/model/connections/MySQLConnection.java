package com.controllwork.javaprogramm.model.connections;

import java.sql.*;

public class MySQLConnection extends ConnectionsToDB{
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public MySQLConnection(String HOST, String USERNAME, String PASSWORD) {
        super(HOST, USERNAME, PASSWORD);
    }

    public java.sql.Connection сonnect() throws SQLException {
        return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    }

    public void getAllcloseConnectionResurses(Connection connection, Statement statement, ResultSet resultSet){
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public void closeAllConnectionResurses() throws SQLException {
        statement.close();
        connection.close();
    }

}
