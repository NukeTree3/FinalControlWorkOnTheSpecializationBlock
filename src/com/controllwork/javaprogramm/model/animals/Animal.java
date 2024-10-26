package com.controllwork.javaprogramm.model.animals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Animal implements Serializable {
    LocalDate birthdate;
    String name;
    ArrayList<String> commands;
    TypeOfAnimal type;

    public Animal(String name, LocalDate birthdate, ArrayList<String> commands){
        this.name = name;
        this.birthdate = birthdate;
        this.commands = commands;
    }

    public Animal(String name, LocalDate birthdate, ArrayList<String> commands, String type){
        this.name = name;
        this.birthdate = birthdate;
        this.commands = commands;
        this.type = TypeOfAnimal.valueOf(type);
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

    public LocalDate getBirthdate(){
        return birthdate;
    }

    public String getAnimal(){
        return "'"+name+"', "+"'"+birthdate+"', "+"'"+String.join(", ",commands)+"'";
    }
    public TypeOfAnimal getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
