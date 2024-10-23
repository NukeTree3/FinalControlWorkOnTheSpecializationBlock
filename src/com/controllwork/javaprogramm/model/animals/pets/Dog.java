package com.controllwork.javaprogramm.model.animals.pets;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dog extends Animal {
    final String type = "dog";
    public Dog(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    public String getType(){
        return type;
    }
}
