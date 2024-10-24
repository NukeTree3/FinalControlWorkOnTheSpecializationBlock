package com.controllwork.javaprogramm.model.filehandler;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.io.IOException;
import java.util.ArrayList;

public interface Writeble {
    void savedAnimalList(ArrayList<Animal> animalList) throws IOException;
    ArrayList<Animal> downloadAnimalList() throws IOException, ClassNotFoundException;;
}
