package com.controllwork.javaprogramm.model.animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public abstract class Animal {
    LocalDate birthdate;
    String name;
    ArrayList<String> commands;
    String type;

    public Animal(String name, LocalDate birthdate, ArrayList<String> commands){
        this.name = name;
        this.birthdate = birthdate;
        this.commands = commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
    public void addCommand(String[] strings){
        this.commands.addAll(Arrays.asList(strings));
    }
    public ArrayList<String> getCommands(){
        return commands;
    }

    public String getAnimal(){
        return "'"+name+"', "+"'"+birthdate+"', "+"'"+commands.toString()+"'";
    }
    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
