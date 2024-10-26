package com.controllwork.javaprogramm.model.filehandler;

import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalCahgesList;
import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalList;

import java.io.IOException;
import java.util.ArrayList;

public interface Writeble {
    void savedAnimalList(AnimalList animalList) throws IOException;
    AnimalList downloadAnimalList() throws IOException, ClassNotFoundException;

    void savedAnimalChangesList(AnimalCahgesList animalList) throws IOException;
    AnimalCahgesList downloadChangesAnimalList() throws IOException, ClassNotFoundException;
}
