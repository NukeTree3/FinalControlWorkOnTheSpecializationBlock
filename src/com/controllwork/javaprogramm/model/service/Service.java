package com.controllwork.javaprogramm.model.service;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;
import com.controllwork.javaprogramm.model.animals.packanimals.Camel;
import com.controllwork.javaprogramm.model.animals.packanimals.Donkey;
import com.controllwork.javaprogramm.model.animals.packanimals.Horse;
import com.controllwork.javaprogramm.model.animals.pets.Cat;
import com.controllwork.javaprogramm.model.animals.pets.Dog;
import com.controllwork.javaprogramm.model.animals.pets.Hamster;
import com.controllwork.javaprogramm.model.filehandler.FileHandler;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Service {

    ArrayList<String> typeOfAnimal = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    FileHandler fileHandler = new FileHandler();


    public Service(){
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.camel));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.donkey));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.horse));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.cat));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.dog));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.hamster));
    }

    public Animal createAnimal(String name, LocalDate birthdate, ArrayList<String> commands, String type){
        Animal animal;
        switch (type) {
             case "camel" -> animal = new Camel(name, birthdate, commands);
             case "donkey" -> animal = new Donkey(name, birthdate, commands);
             case "horse" -> animal = new Horse(name, birthdate, commands);
             case "cat" -> animal = new Cat(name, birthdate, commands);
             case "dog" -> animal = new Dog(name, birthdate, commands);
             case "hamster" -> animal = new Hamster(name, birthdate, commands);
             default -> animal = null;
        };
        if (animal != null){
            backupAnimals(animal);
        }
        return animal;
    }

    private ResultSet getFromDB(String string) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        if(connection!=null){
            Statement statement = connection.createStatement();
            //connection.close();
            return statement.executeQuery(string);
        }
        else{
            //connection.close();
            return null;
        }
    }

    private int updateDB(String string) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        int resultSet = 0;
        if(connection!=null){
            Statement statement = connection.createStatement();
            resultSet = statement.executeUpdate(string);
        }
        return resultSet;
    }

    public String getAnimal(String animalName) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("id"));
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("%##%");
            }
        }
        return stringBuilder.toString();
    }

    public String getAnimalById(String id) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE id = '" + id+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append(resultSet.getString("commands"));
            }
        }
        return stringBuilder.toString();
    }

    public int addAnimal(Animal animal) throws SQLException {
        return updateDB("INSERT INTO all_animals (name, birthdate, commands, type) VALUES ("+animal.getAnimal()+", "+"'"+animal.getType()+"')");
    }

    public String getAnimalCommand(String animalName) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("commands"));
            }
        }
        return stringBuilder.toString();
    }

    public int addAnimalCommand(Animal animal, String command) throws SQLException {
        return updateDB("UPDATE all_animals SET commands = '" + getAnimalCommand(animal.getName())+","+command + "' WHERE name = '" + animal.getName()+"'");
    }

    public String getAnimalOrderByBirthdate() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals ORDER BY birthdate DESC");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("/n");
            }
        }
        return stringBuilder.toString();
    }

    public String getAnimalOrderByBirthdate(String min, String max) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE birthdate BETWEEN '"+min+"' AND '"+max+"' ORDER BY birthdate DESC");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("/n");
            }
        }
        return stringBuilder.toString();
    }

    public String outputAll() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("type"));
                stringBuilder.append(" - ");
                stringBuilder.append(resultSet.getString("name"));
//                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public int getCount() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT COUNT(*) AS count FROM all_animals");
        if(resultSet.next()){
            return resultSet.getInt("count");
        }
        return 0;
    }

    public void backupAnimals(Animal animal){
        animals.add(animal);
    }

    public void saveAnimals(ArrayList<Animal> animals) throws IOException {
        fileHandler.savedAnimalList(animals);
    }

    public void downloadAnimals() throws IOException, ClassNotFoundException {
        animals = fileHandler.downloadAnimalList();
    }
}