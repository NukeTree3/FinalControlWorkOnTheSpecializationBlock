package com.controllwork.javaprogramm.model.filehandler;

import com.controllwork.javaprogramm.model.animals.Animal;

import java.io.*;
import java.util.ArrayList;

public class FileHandler implements Writeble{
    @Override
    public void savedAnimalList(ArrayList<Animal> animalList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./src/savesTimeLine.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(animalList);
        objectOutputStream.close();
        fileOutputStream.close();
    }


    @Override
    public ArrayList<Animal> downloadAnimalList() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./src/savesTimeLine.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Animal> animalList = (ArrayList<Animal>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return animalList;
    }


}
