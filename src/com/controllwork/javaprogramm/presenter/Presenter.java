package com.controllwork.javaprogramm.presenter;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.service.Service;
import com.controllwork.javaprogramm.view.View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Presenter {
    Service service;
    View view;

    public Presenter(View view){
        this.view = view;
        service = new Service();
    }


    public int getCount() throws SQLException {
        return service.getCount();
    }

    public String getAnimal(String name) throws SQLException {
        return service.getAnimal(name);
    }

    public String getAnimalById(String id) throws SQLException {
        return service.getAnimalById(id);
    }

    public String getAnimalCommand(String name) throws SQLException {
        return service.getAnimalCommand(name);
    }

    public String getAnimalOrderByBirthdate() throws SQLException {
        return service.getAnimalOrderByBirthdate();
    }

    public String getAnimalOrderByBirthdate(String min, String max) throws SQLException {
        return service.getAnimalOrderByBirthdate(min, max);
    }

    public String outputAll() throws SQLException {
        return service.outputAll();
    }

    public int addAnimalCommand(String name, String command) throws SQLException {
        return service.addAnimalCommand(service.getAnimalLikeClassFromTable(name), command);
    }

    public int addAnimal(String name, String birthdate, String commands, String type) throws SQLException {
        return service.addAnimal(createAnimal(name,birthdate,commands,type));
    }

    public boolean getAutoSend(){
        return service.getAutoSend();
    }

    public void autoSend(boolean flag){
        service.autoSend(flag);
    }

    public void sendBackup() throws SQLException {
        service.sendBackup();
    }

    public String inputAllBackups(){
        return service.inputAllBackups();
    }

    private Animal createAnimal(String name, String birthdate, String commands, String type){
        return service.createAnimal(name,birthdate,commands,type);
    }
}