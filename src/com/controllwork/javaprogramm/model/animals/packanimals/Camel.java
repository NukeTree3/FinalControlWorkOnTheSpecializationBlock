package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Camel extends Animal {
    final TypeOfAnimal type = TypeOfAnimal.camel;
    public Camel(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
}
