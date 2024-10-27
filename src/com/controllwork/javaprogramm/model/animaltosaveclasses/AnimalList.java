package com.controllwork.javaprogramm.model.animaltosaveclasses;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimalList implements Serializable {
    ArrayList<Animal> newAnimalList = new ArrayList<>();
    public void addAnimalToAnimalList(Animal newAnimal){
        newAnimalList.add(newAnimal);
    }
    public ArrayList<Animal> getAnimalList(){
        return newAnimalList;
    }
}
