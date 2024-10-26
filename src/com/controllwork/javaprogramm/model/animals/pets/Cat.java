package com.controllwork.javaprogramm.model.animals.pets;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cat extends Animal {
    final TypeOfAnimal type = TypeOfAnimal.cat;
    public Cat(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    @Override
    public TypeOfAnimal getType(){
        return type;
    }
}
