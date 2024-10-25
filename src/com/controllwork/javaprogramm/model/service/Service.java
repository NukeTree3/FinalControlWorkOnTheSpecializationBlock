package com.controllwork.javaprogramm.model.service;

import com.controllwork.javaprogramm.model.AnimalChanges;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {

    ArrayList<String> typeOfAnimal = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<AnimalChanges> animalsChangeList = new ArrayList<>();
    FileHandler fileHandler = new FileHandler();
    Boolean flag = true;


    public Service(){
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.camel));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.donkey));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.horse));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.cat));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.dog));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.hamster));
    }

    public Animal createAnimal(String name, String stringBirthdate, String stringCommands, String type){
        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(stringBirthdate);
        }
        catch (DateTimeParseException e){
            return null;
        }

        ArrayList<String> commands = new ArrayList<>();
        Collections.addAll(commands, stringCommands.split(", "));
        System.out.println(type);
        System.out.println(type.equals("cat"));
        switch (type) {
             case "camel" -> {Camel animal = new Camel(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
             case "donkey" -> {Donkey animal = new Donkey(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
             case "horse" -> {Horse animal = new Horse(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
             case "cat" -> {Cat animal = new Cat(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
             case "dog" -> {Dog animal = new Dog(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
             case "hamster" -> {Hamster animal = new Hamster(name, birthdate, commands);
                 backupAnimals(animal);
                 return animal;}
            default -> {
                 return null;
            }
        }
    }

    private ResultSet getFromDB(String string) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        if(connection!=null){
            Statement statement = connection.createStatement();
            return statement.executeQuery(string);
        }
        else{
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
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("%##%");
            }
        }
        return stringBuilder.toString();
    }

    public Animal getAnimalLikeClassFromTable(String animalName) throws SQLException {
        if (animalInBackup(animalName)){
            return getAnimalFromBackup(animalName);
        }
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        ArrayList<Animal> tempAnimals = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()) {
                tempAnimals.add(createAnimal(resultSet.getString("name"),resultSet.getString("birthdate"),resultSet.getString("commands"),resultSet.getString("type")));
            }
        }
        if (tempAnimals.size()==1){
            //animalsChange.add(tempAnimals.getFirst());
            return tempAnimals.getFirst();
        }
        return null;
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
        if(animal!=null){
            if(flag){
                if(animalInBackup(animal)){
                    animals.remove(animal);
                }
                return updateDB("INSERT INTO all_animals (name, birthdate, commands, type) VALUES ("+animal.getAnimal()+", "+"'"+animal.getType()+"')");
            }
            else {
                backupAnimals(animal);
                return -2;
            }
        }
        else return -1;
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
        AnimalChanges animalChanges = new AnimalChanges(animal,command);
        if (animal != null){
            if(flag){
                for (int i = 0; i < animalsChangeList.size(); i++) {
                    if(animalsChangeList.get(i).equals(animalChanges)){
                        animalsChangeList.remove(i);
                    }
                }
                return updateDB("UPDATE all_animals SET commands = '" + String.join(", ",animal.getCommands())+","+ command + "' WHERE name = '" + animal.getName()+"'");
            }
            else {
                backupAnimals(animal,command);
            }
        }
        return -1;
    }

    public String getAnimalOrderByBirthdate() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals ORDER BY birthdate DESC");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("#&&#");
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
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append("@@");
                stringBuilder.append(resultSet.getString("commands"));
                stringBuilder.append("#&&#");
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
    public void backupAnimals(Animal animal, String command){
        AnimalChanges animalChanges = new AnimalChanges(animal,command);
        animalsChangeList.add(animalChanges);
    }

    public boolean animalInBackup(Animal animal){
        if(animals.contains(animal)){
            return true;
        }
        return false;
    }

    public boolean animalInBackup(String animalName){
        for (Animal animal: animals) {
            if (animal.getName().equals(animalName)){
                return true;
            }
        }
        return false;
    }

    public boolean animalChangeInBackup(Animal animal){
        for (AnimalChanges animalChange: animalsChangeList) {
            if (animal.equals(animalChange.getAnimal())){
                return true;
            }
        }
        return false;
    }

    public Animal getAnimalFromBackup(String animalName){
        for (Animal animal: animals) {
            if (animal.getName().equals(animalName)){
                return animal;
            }
        }
        return null;
    }

    public void saveAnimals(ArrayList<Animal> animals) throws IOException {
        fileHandler.savedAnimalList(animals);
    }

    public void downloadAnimals() throws IOException, ClassNotFoundException {
        animals = fileHandler.downloadAnimalList();
    }

    public void autoSend(boolean flag){
        this.flag = flag;
    }

    public boolean getAutoSend(){
        return flag;
    }

    public void sendBackup() throws SQLException {
        flag=true;
        while (!animalsChangeList.isEmpty()){
            addAnimalCommand(animalsChangeList.getFirst().getAnimal(), animalsChangeList.getFirst().getCommand());
        }
        while (!animals.isEmpty()){
            addAnimal(animals.getFirst());
        }
    }

    public String inputAllBackups(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("добавленные животные на локалном хранении");
        for (Animal animal: animals) {
            stringBuilder.append(animal.getAnimal());
        }
        stringBuilder.append("добавленные изменения животных на локалном хранении");
        for (AnimalChanges animalch: animalsChangeList) {
            stringBuilder.append(animalch.getAnimal().getAnimal() + " добавленная информаци " + animalch.getCommand());
        }
        return stringBuilder.toString();
    }
}