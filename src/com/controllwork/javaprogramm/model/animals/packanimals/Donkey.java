package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Donkey extends Animal {
    final TypeOfAnimal type = TypeOfAnimal.donkey;
    public Donkey(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
}
