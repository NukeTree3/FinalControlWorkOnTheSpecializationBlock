package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camel extends Animal implements Serializable {
    final TypeOfAnimal type = TypeOfAnimal.donkey;

    public Camel(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    @Override
    public TypeOfAnimal getType(){
        return type;
    }
}
