package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Donkey extends Animal {
    final String type = "donkey";
    public Donkey(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    public String getType(){
        return type;
    }
}
