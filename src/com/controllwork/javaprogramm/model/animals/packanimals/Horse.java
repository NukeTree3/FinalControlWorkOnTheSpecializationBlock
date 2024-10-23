package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Horse extends Animal {
    final String type = "horse";
    public Horse(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    public String getType(){
        return type;
    }
}
