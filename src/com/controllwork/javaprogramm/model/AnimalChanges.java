package com.controllwork.javaprogramm.model;

import com.controllwork.javaprogramm.model.animals.Animal;

public class AnimalChanges {
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
