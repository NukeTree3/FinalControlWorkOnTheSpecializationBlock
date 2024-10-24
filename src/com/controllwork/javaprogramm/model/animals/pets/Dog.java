package com.controllwork.javaprogramm.model.animals.pets;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dog extends Animal {
    final TypeOfAnimal type = TypeOfAnimal.dog;
    public Dog(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
}
