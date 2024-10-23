package com.controllwork.javaprogramm.model.animals.pets;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cat extends Animal {
    final String type = "cat";
    public Cat(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    public String getType(){
        return type;
    }
}
