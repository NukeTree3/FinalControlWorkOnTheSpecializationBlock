package com.controllwork.javaprogramm.view;

import com.controllwork.javaprogramm.presenter.Presenter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleUI implements View {
    private static final String INPUT_ERROR = "Ошибка ввода";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu mainMenu;

    public ConsoleUI(){
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        mainMenu = new MainMenu(this);
    }

    @Override
    public void start() throws IOException, ClassNotFoundException, SQLException {
        helloWorld();
        while (work){
            printMenu();
            execute();
        }
    }


    public void finish(){
        System.out.println("Bye, world");
        work=false;
    }

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    public void helloWorld(){
        System.out.println("Hello world");
    }

    public void printMenu(){
        System.out.println();
        System.out.println(mainMenu.outputMenuInfo());
    }


    private void execute() throws IOException, ClassNotFoundException, SQLException {
        String line = scanner.nextLine();
        if (checkTextForInt(line)){
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)){
                mainMenu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand){
        if (numCommand <= mainMenu.getSize()){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private void inputError(){
        System.out.println(INPUT_ERROR);
    }

    public void getCount() throws SQLException {
        System.out.println("Общее количество зарегистрированнх животных: "+presenter.getCount());
    }

    public void outputAllAnimals() throws SQLException {
        System.out.println(presenter.outputAll());
    }

    public void getAnimalById() throws SQLException {
        System.out.println("Введите имя животного");
        String output = presenter.getAnimalById(scanner.nextLine());
        System.out.println(output);
    }

    public void getAnimal() throws SQLException {
        System.out.println("Введите имя животного");
        String output = presenter.getAnimal(scanner.nextLine());
        if (!output.equals("\n")){
            if (output.split("%##%").length>1){
                System.out.println("по вашему запросу найдено несколько значений");
                for (String animal: output.split("%##%")) {
                    System.out.println(animal);
                }
                System.out.println("Если нужно конкретное животное, обращайтесь по индексу");
            }
            else {
                System.out.println(output);
            }
        }
        else {
            System.out.println("такого животного нет");
        }


    }

//    public void save() throws IOException {
//        presenter.save();
//    }
//
//    public void download() throws IOException, ClassNotFoundException {
//        presenter.download();
//    }
}
