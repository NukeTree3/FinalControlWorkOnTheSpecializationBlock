package com.controllwork.javaprogramm.model.animals.pets;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hamster extends Animal {
    TypeOfAnimal type = TypeOfAnimal.hamster;

    public Hamster(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    @Override
    public TypeOfAnimal getType(){
        return type;
    }
}
