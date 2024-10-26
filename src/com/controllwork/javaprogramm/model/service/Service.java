package com.controllwork.javaprogramm.model.service;

import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalCahgesList;
import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalChanges;
import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;
import com.controllwork.javaprogramm.model.animals.packanimals.Camel;
import com.controllwork.javaprogramm.model.animals.packanimals.Donkey;
import com.controllwork.javaprogramm.model.animals.packanimals.Horse;
import com.controllwork.javaprogramm.model.animals.pets.Cat;
import com.controllwork.javaprogramm.model.animals.pets.Dog;
import com.controllwork.javaprogramm.model.animals.pets.Hamster;
import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalList;
import com.controllwork.javaprogramm.model.connections.MySQLConnection;
import com.controllwork.javaprogramm.model.filehandler.FileHandler;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Service {

    ArrayList<String> typeOfAnimal = new ArrayList<>();
    AnimalList animals;
    //ArrayList<AnimalChanges> animalsChangeList = new ArrayList<>();
    AnimalCahgesList animalCahgesList;
    FileHandler fileHandler = new FileHandler();
    Boolean flag = true;
    MySQLConnection mySQLConnection;
//    ResultSet resultSet;
//    Statement statement;
//    Connection connection;




    public Service() throws IOException, ClassNotFoundException {
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.camel));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.donkey));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.horse));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.cat));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.dog));
        typeOfAnimal.add(String.valueOf(TypeOfAnimal.hamster));
        if (downloadAnimals()!=null){
            animals = downloadAnimals();
        }else {
            animals = new AnimalList();
        }
        if (downloadAnimalsChanges()!=null){
            animalCahgesList = downloadAnimalsChanges();
        }else {
            animalCahgesList = new AnimalCahgesList();
        }

        mySQLConnection = new MySQLConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");

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
        switch (type) {
             case "camel" -> {Camel animal = new Camel(name, birthdate, commands);
                 return animal;}
             case "donkey" -> {Donkey animal = new Donkey(name, birthdate, commands);
                 return animal;}
             case "horse" -> {Horse animal = new Horse(name, birthdate, commands);
                 return animal;}
             case "cat" -> {Cat animal = new Cat(name, birthdate, commands);
                 return animal;}
             case "dog" -> {Dog animal = new Dog(name, birthdate, commands);
                 return animal;}
             case "hamster" -> {Hamster animal = new Hamster(name, birthdate, commands);
                 return animal;}
            default -> {
                 return null;
            }
        }
    }

    private ResultSet getFromDB(String string) throws SQLException {

        Connection connection = mySQLConnection.сonnect();
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        if(connection!=null){
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery(string);
            mySQLConnection.getAllcloseConnectionResurses(connection,statement,resultSet1);
            //connection.close();
            return resultSet1;
        }
        else{
            //connection.close();
            return null;
        }
    }

    private int updateDB(String string) throws SQLException {
        Connection connection = mySQLConnection.сonnect();
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
        int resultSet = 0;
        if(connection!=null){
            Statement statement = connection.createStatement();
            resultSet = statement.executeUpdate(string);
        }
        int res = resultSet;
        //assert connection != null;
        connection.close();
        return res;
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
        mySQLConnection.closeAllConnectionResurses();
        return stringBuilder.toString();
    }

    public Animal getAnimalLikeClassFromTable(String animalName) throws SQLException {
        if (animalCahgesList.nameInAnimalChanges(animalName)){
            return animalCahgesList.GetAnimalChanges(animalName);
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
            mySQLConnection.closeAllConnectionResurses();
            return tempAnimals.getFirst();
        }
        mySQLConnection.closeAllConnectionResurses();
        return null;
    }

    public Animal getAnimalLikeClassFromTableById(String id) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE id = '" + id+"'");
        ArrayList<Animal> tempAnimals = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()) {
                tempAnimals.add(createAnimal(resultSet.getString("name"),resultSet.getString("birthdate"),resultSet.getString("commands"),resultSet.getString("type")));
            }
        }
        if (tempAnimals.size()==1){
            //animalsChange.add(tempAnimals.getFirst());
            mySQLConnection.closeAllConnectionResurses();
            return tempAnimals.getFirst();
        }
        mySQLConnection.closeAllConnectionResurses();
        return null;
    }

    public String getAnimalById(String id) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE id = '" + id+"'");
        if (resultSet != null){
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
                stringBuilder.append(" ");
                stringBuilder.append(resultSet.getString("birthdate"));
                stringBuilder.append(" ");
                stringBuilder.append(resultSet.getString("commands"));
            }
        }
        mySQLConnection.closeAllConnectionResurses();
        return stringBuilder.toString();
    }

    public int addAnimal(Animal animal) throws SQLException {
        if(animal!=null){
            if(flag){
                if(animalInBackup(animal)){
                    animals.getAnimalList().remove(animal);
                    //animals.remove(animal);
                    System.out.println("#############@@@@@@@@@@$$$$$$$$$$$$$$");
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
        //StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = getFromDB("SELECT * FROM all_animals WHERE name = '" + animalName+"'");
        ArrayList<String> arrayList = new ArrayList<>();
        if (resultSet != null){
            while (resultSet.next()) {
                arrayList.add(resultSet.getString("commands"));
            }
        }
        mySQLConnection.closeAllConnectionResurses();
        if(arrayList.size()==1){
            return arrayList.getFirst();
        }
        else if(arrayList.size()>1){
            return "Найдено несколько результатов, воспользуйтесь командой вывода по id";
        }
        return "Нет такого животного";
    }

    public int addAnimalCommand(Animal animal, String command) throws SQLException {
        AnimalChanges animalChanges = new AnimalChanges(animal,command);
        if (animal != null){
            if(flag){
                System.out.println("^&^&^&^&^&^&^&^&^&^&^&^&");
                for (int i = 0; i < animalCahgesList.getAnimalChanges().size(); i++) {
                    System.out.println("{{}{}{}{}{}}{}{{}{}{}");
                    if(animalCahgesList.getAnimalChanges().get(i).getAnimal().equals(animalChanges.getAnimal()) && animalCahgesList.getAnimalChanges().get(i).getCommand().equals(animalChanges.getCommand())){
                        animalCahgesList.getAnimalChanges().remove(i);
                        System.out.println(")))))))))))))))))))))");
                    }
                }
                if(getCountToName(animal.getName())==1){
                    return updateDB("UPDATE all_animals SET commands = '" + String.join(", ",animal.getCommands())+","+ command + "' WHERE name = '" + animal.getName()+"'");
                }
                else return -2;
            }
            else {
                backupAnimals(animal,command);
            }
        }
        return -1;
    }

    public int addAnimalCommandById(String id,String command) throws SQLException {
        Animal animal = getAnimalLikeClassFromTableById(id);
        AnimalChanges animalChanges = new AnimalChanges(animal,command);
        if (animal != null){
            if(flag){
                for (int i = 0; i < animalCahgesList.getAnimalChanges().size(); i++) {
                    if(animalCahgesList.getAnimalChanges().get(i).equals(animalChanges)){
                        animalCahgesList.getAnimalChanges().remove(i);
                    }
                }
                if(getCountToName(animal.getName())==1){
                    return updateDB("UPDATE all_animals SET commands = '" + String.join(", ",animal.getCommands())+","+ command + "' WHERE id = '" + id+"'");
                }
                else return -2;
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
        mySQLConnection.closeAllConnectionResurses();
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
        mySQLConnection.closeAllConnectionResurses();
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
        mySQLConnection.closeAllConnectionResurses();
        return stringBuilder.toString();
    }

    public int getCount() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT COUNT(*) AS count FROM all_animals");
        //System.out.println(resultSet.getString(0));
        if(resultSet.next()){
            int a = resultSet.getInt("count");
            mySQLConnection.closeAllConnectionResurses();
            return a;
        }
        mySQLConnection.closeAllConnectionResurses();
        return 0;
    }

    public int getCountToName(String name) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT COUNT(*) AS count FROM all_animals WHERE name = '" + name+"'");
        if (resultSet.next()) {
            int a = resultSet.getInt("count");
            mySQLConnection.closeAllConnectionResurses();
            return a;
        }
        mySQLConnection.closeAllConnectionResurses();
        return 0;
    }

    public void backupAnimals(Animal animal){
        animals.addAnimalToAnimalList(animal);
    }
    public void backupAnimals(Animal animal, String command){
        AnimalChanges animalChanges = new AnimalChanges(animal,command);
        animalCahgesList.addAnimalChage(animalChanges);
    }

    public boolean animalInBackup(Animal animal){
        if(animals.getAnimalList().contains(animal)){
            return true;
        }
        return false;
    }

    public boolean animalInBackup(String animalName){
        for (Animal animal: animals.getAnimalList()) {
            if (animal.getName().equals(animalName)){
                return true;
            }
        }
        return false;
    }

    public boolean animalChangeInBackup(Animal animal){
        for (AnimalChanges animalChange: animalCahgesList.getAnimalChanges()) {
            if (animal.equals(animalChange.getAnimal())){
                return true;
            }
        }
        return false;
    }

    public Animal getAnimalFromBackup(String animalName){
        for (Animal animal: animals.getAnimalList()) {
            if (animal.getName().equals(animalName)){
                return animal;
            }
        }
        return null;
    }

    public void saveAnimals(AnimalList animals) throws IOException {
        fileHandler.savedAnimalList(animals);
    }

    public AnimalList downloadAnimals() throws IOException, ClassNotFoundException {
        return fileHandler.downloadAnimalList();
    }

    public void saveAnimalsChanges(AnimalCahgesList animalCahgesList) throws IOException {
        fileHandler.savedAnimalChangesList(animalCahgesList);
    }

    public void saveAll() throws IOException {
        saveAnimals(animals);
        saveAnimalsChanges(animalCahgesList);
    }
    public AnimalCahgesList downloadAnimalsChanges() throws IOException, ClassNotFoundException {
        return fileHandler.downloadChangesAnimalList();
    }

    public void autoSend(boolean flag){
        this.flag = flag;
    }

    public boolean getAutoSend(){
        return flag;
    }

    public String sendBackup() throws SQLException {
        String s = "";
        boolean temp = flag;
        flag=true;
        System.out.println("_________");
        while (!animals.getAnimalList().isEmpty()){
            addAnimal(animals.getAnimalList().getFirst());
            System.out.println("########");
        }
        while (!animalCahgesList.getAnimalChanges().isEmpty()){
            int a = addAnimalCommand(animalCahgesList.getAnimalChanges().getFirst().getAnimal(), animalCahgesList.getAnimalChanges().getFirst().getCommand());
            System.out.println("_________");
            if(a==-1){
                s = a+"";
                System.out.println("++++++++++++++++++++++++");
                break;
            }
        }
        flag = temp;
        return s;
    }

    public String inputAllBackups(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("добавленные животные на локалном хранении\n");
        for (Animal animal: animals.getAnimalList()) {
            stringBuilder.append(animal.getAnimal());
            stringBuilder.append("\n");
        }
        stringBuilder.append("\nдобавленные изменения животных на локалном хранении\n");
        for (AnimalChanges animalch: animalCahgesList.getAnimalChanges()) {
            stringBuilder.append(animalch.getAnimal().getAnimal() + " добавленная информаци " + animalch.getCommand());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}