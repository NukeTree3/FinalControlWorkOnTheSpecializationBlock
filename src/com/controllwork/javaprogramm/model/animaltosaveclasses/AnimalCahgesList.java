package com.controllwork.javaprogramm.model.animaltosaveclasses;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimalCahgesList implements Serializable {
    ArrayList<AnimalChanges> animalChanges = new ArrayList<>();
    public void addAnimalChage(AnimalChanges animalChange){
        animalChanges.add(animalChange);
    }

    public ArrayList<AnimalChanges> getAnimalChanges(){
        return animalChanges;
    }

    public boolean nameInAnimalChanges(String name){
        for (AnimalChanges animalch: animalChanges) {
            if (animalch.getAnimal().getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Animal GetAnimalChanges(String name){
        for (AnimalChanges animalch: animalChanges) {
            if (animalch.getAnimal().getName().equals(name)){
                return animalch.getAnimal();
            }
        }
        return null;
    }
}
