package com.controllwork.javaprogramm.view;

import com.controllwork.javaprogramm.presenter.Presenter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
                System.out.println("Если нужно конкретное животное, обращайтесь по id");
            }
            else {
                System.out.println(output);
            }
        }
        else {
            System.out.println("такого животного нет");
        }


    }

    public void getAnimalOrderByBirthdate() throws SQLException {
        System.out.println("Хотите выбрать временной промежуток(1(да)/2(нет))");
        String s = scanner.nextLine();
        if(s.equals("1")){
            System.out.println("введите начальную дату");
            String minDate = scanner.nextLine();
            System.out.println("введите конечную дату");
            String maxDate = scanner.nextLine();
            try {
                LocalDate min = LocalDate.parse(minDate);
                LocalDate max = LocalDate.parse(maxDate);
                output(presenter.getAnimalOrderByBirthdate(minDate,maxDate));
            }
            catch (DateTimeParseException e){
                System.out.println("Неверно указана дата");
            }
        }
        else if(s.equals("2")){
            output(presenter.getAnimalOrderByBirthdate());
        }
        else {
            System.out.println(INPUT_ERROR);
        }
    }
    
    private void output(String resault){
        for (String line: resault.split("#&&#")) {
            for (String column:line.split("@@")) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public void getAnimalCommand() throws SQLException {
        System.out.println("введите имя животного");
        String name = scanner.nextLine();
        System.out.println(presenter.getAnimalCommand(name));
    }

    public void addAnimal() throws SQLException {
        System.out.println("введите имя");
        String name = scanner.nextLine();
        System.out.println("введите дату рождения");
        String birthdate = scanner.nextLine();
        System.out.println("введите команды");
        String commands = scanner.nextLine();
        System.out.println("введите тип животного");
        String type = scanner.nextLine();
        int status = presenter.addAnimal(name,birthdate,commands,type);
        if(status==-1){
            inputError();
        }
        else if(status == 2){
            System.out.println("сохранен в внутренне хранилище");
        }
        else {
            System.out.println("готово");
        }
    }

    public void addAnimalCommand() throws SQLException {
        System.out.println("введите имя");
        String name = scanner.nextLine();
        System.out.println("введите комманду");
        String command = scanner.nextLine();
        presenter.addAnimalCommand(name, command);
    }

    public void autoSend(){
        String s="";
        if(!presenter.getAutoSend()){
            s="локальный режим, все изменения сохраняются локально";
        }
        else {
            s="режим автоотправки, все ваши изменения автоматически отправляются на сервер";
        }
        System.out.println("уважаемы пользователь, у вас включен "+s+" хотите его поменять? 1(да)/2(нет)");
        s = scanner.nextLine();
        if(Objects.equals(s, "1")){
            presenter.autoSend(false);
            System.out.println("режим изменен");
        }else if(Objects.equals(s, "2")) {
            System.out.println("режим не изменен");
        }
        else {
            inputError();
        }
    }

    public void sendBackup() throws SQLException {
        System.out.println("уважаемы пользователь, у вас есть следующие данные, готовые к отправке");
        System.out.println(presenter.inputAllBackups());
        System.out.println("отправить их на сервер? 1(да)/2(нет)");
        String s = scanner.nextLine();
        if(Objects.equals(s, "1")) {
            presenter.sendBackup();
            System.out.println("отправлены");
        }
        else {
            inputError();
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
