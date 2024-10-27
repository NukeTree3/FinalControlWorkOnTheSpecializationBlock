package com.controllwork.javaprogramm.model.filehandler;

import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalCahgesList;
import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animaltosaveclasses.AnimalList;

import java.io.*;
import java.util.ArrayList;

public class FileHandler implements Writeble {
    @Override
    public void savedAnimalList(AnimalList animalList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/com/controllwork/javaprogramm/model/savesfiles/savedAnimalList.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(animalList);
        objectOutputStream.close();
        fileOutputStream.close();
    }


    @Override
    public AnimalList downloadAnimalList() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/com/controllwork/javaprogramm/model/savesfiles/savedAnimalList.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            AnimalList animalList = (AnimalList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return animalList;
        }
        catch (Exception e){
            return null;
        }
    }
    @Override
    public void savedAnimalChangesList(AnimalCahgesList animalList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/com/controllwork/javaprogramm/model/savesfiles/savedAnimalChangesList.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(animalList);
        objectOutputStream.close();
        fileOutputStream.close();
    }


    @Override
    public AnimalCahgesList downloadChangesAnimalList() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/com/controllwork/javaprogramm/model/savesfiles/savedAnimalChangesList.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            AnimalCahgesList animalList = (AnimalCahgesList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return animalList;
        }
        catch (Exception e){
            return null;
        }
    }
}