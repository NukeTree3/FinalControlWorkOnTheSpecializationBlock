package com.controllwork.javaprogramm.model.animals.packanimals;

import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.TypeOfAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Horse extends Animal {
    final TypeOfAnimal type = TypeOfAnimal.horse;
    public Horse(String name, LocalDate birthdate, ArrayList<String> commands) {
        super(name, birthdate, commands);
    }
    @Override
    public TypeOfAnimal getType(){
        return type;
    }
}
