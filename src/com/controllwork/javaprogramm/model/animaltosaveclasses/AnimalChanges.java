package com.controllwork.javaprogramm.model.animaltosaveclasses;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.io.Serializable;

public class AnimalChanges implements Serializable {
    Animal animal;
    String command;

    public AnimalChanges(Animal animal, String command){
        this.animal = animal;
        this.command = command;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getCommand() {
        return command;
    }
}
