package com.controllwork.javaprogramm.model.animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Animal {
    LocalDate birthdate;
    String name;
    ArrayList<String> commands;
    TypeOfAnimal type;

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
    public TypeOfAnimal getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
