package com.controllwork.javaprogramm.model.service;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.sql.*;

public class Service {

    private ResultSet getFromDB(String string) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        if(connection!=null){
            System.out.println("ЯПИЯПИЯПИ");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(string);
            return resultSet;
        }
        else{
            System.out.println("((((((((((");
            return null;
        }
    }

    private int updateDB(String string) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        int resultSet = 0;
        if(connection!=null){
            System.out.println("ЯПИЯПИЯПИ");
            Statement statement = connection.createStatement();
            resultSet = statement.executeUpdate(string);
        }
        else{
            System.out.println("((((((((((");
        }
        return resultSet;
    }



    public void getAnimal(String animalName) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("birthdate"));
                System.out.println(resultSet.getString("commands"));
            }
        }
        else {
            System.out.println("(((((((((((((((");
        }
    }

    public void addAnimal(Animal animal) throws SQLException {
        System.out.println("INSERT INTO all_animals (name, birthdate, commands, type) VALUES  ("+animal.getAnimal()+", "+"'"+animal.getType()+"')");
        int a = updateDB("INSERT INTO all_animals (name, birthdate, commands, type) VALUES ("+animal.getAnimal()+", "+"'"+animal.getType()+"')");
        System.out.println(a);
    }

    public String getAnimalCommand(String animalName) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                System.out.println(resultSet.getString("commands"));
                return resultSet.getString("commands");
            }
        }
        else {
            System.out.println("(((((((((((((((");
        }
        return null;
    }

    public void addAnimalCommand(Animal animal, String command) throws SQLException {
        System.out.println("UPDATE all_animals SET commands = '" + getAnimalCommand(animal.getName())+","+command + "' WHERE name = '" + animal.getName()+"'");
        int a = updateDB("UPDATE all_animals SET commands = '" + getAnimalCommand(animal.getName())+","+command + "' WHERE name = '" + animal.getName()+"'");
        System.out.println(a);
    }

    public void getAnimalOrderByBirthdate() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals ORDER BY birthdate DESC");
        if (resultSet != null){
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("birthdate"));
                System.out.println(resultSet.getString("commands"));
            }
        }
        else {
            System.out.println("(((((((((((((((");
        }
    }

    public void getAnimalOrderByBirthdate(String min, String max) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE birthdate BETWEEN '"+min+"' AND '"+max+"' ORDER BY birthdate DESC");
        if (resultSet != null){
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("birthdate"));
                System.out.println(resultSet.getString("commands"));
            }
        }
        else {
            System.out.println("(((((((((((((((");
        }
    }

    public void outputAll() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals");
        if (resultSet != null){
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("birthdate"));
                System.out.println(resultSet.getString("commands"));
            }
        }
        else {
            System.out.println("(((((((((((((((");
        }
    }
}