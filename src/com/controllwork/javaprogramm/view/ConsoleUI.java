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

    public ConsoleUI() throws IOException, ClassNotFoundException {
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


    public void finish() throws IOException {
        printText("Bye, world");
        presenter.saveAll();
        work=false;
    }

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    public void helloWorld(){
        printText("Hello world");
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
        printText(INPUT_ERROR);
    }

    public void getCount() throws SQLException {
        printText("Общее количество зарегистрированнх животных: "+presenter.getCount());
    }

    public void outputAllAnimals() throws SQLException {
        printText(presenter.outputAll());
    }

    public void getAnimalById() throws SQLException {
        printText("Введите id животного");
        String output = presenter.getAnimalById(scanner.nextLine());
        printText(output);
    }

    public void getAnimal() throws SQLException {
        printText("Введите имя животного");
        String outputString = presenter.getAnimal(scanner.nextLine());
        if (!outputString.equals("\n")){
            if (outputString.split("%##%").length>1){
                printText("по вашему запросу найдено несколько значений");
                output(outputString);
                printText("Если нужно конкретное животное, обращайтесь по id");
            }
            else {
                printText(outputString);
            }
        }
        else {
            printText("такого животного нет");
        }


    }

    public void getAnimalOrderByBirthdate() throws SQLException {
        printText("Хотите выбрать временной промежуток(1(да)/2(нет))");
        String s = scanner.nextLine();
        if(s.equals("1")){
            printText("введите начальную дату");
            String minDate = scanner.nextLine();
            printText("введите конечную дату");
            String maxDate = scanner.nextLine();
            try {
                LocalDate min = LocalDate.parse(minDate);
                LocalDate max = LocalDate.parse(maxDate);
                output(presenter.getAnimalOrderByBirthdate(minDate,maxDate));
            }
            catch (DateTimeParseException e){
                printText("Неверно указана дата");
            }
        }
        else if(s.equals("2")){
            output(presenter.getAnimalOrderByBirthdate());
        }
        else {
            printText(INPUT_ERROR);
        }
    }
    
    private void output(String resault){
        for (String line: resault.split("%##%")) {
            StringBuilder s = new StringBuilder();
            for (String column:line.split("@@")) {
                s.append(column).append(" ");
            }
            printText(String.valueOf(s));
        }
    }

    public void getAnimalCommand() throws SQLException {
        printText("введите имя животного");
        String name = scanner.nextLine();
        printText(presenter.getAnimalCommand(name));
    }

    public void addAnimal() throws SQLException {
        printText("введите имя");
        String name = scanner.nextLine();
        printText("введите дату рождения");
        String birthdate = scanner.nextLine();
        printText("введите команды");
        String commands = scanner.nextLine();
        printText("введите тип животного");
        String type = scanner.nextLine();
        int status = presenter.addAnimal(name,birthdate,commands,type);
        if(status==-1){
            inputError();
        }
        else if(status == -2){
            printText("сохранен в внутренне хранилище");
        }
        else {
            printText("готово");
        }
    }

    public void addAnimalCommand() throws SQLException {
        printText("введите имя");
        String name = scanner.nextLine();
        printText("введите комманду");
        String command = scanner.nextLine();
        int a = presenter.addAnimalCommand(name, command);
        printText(a+"");
        if (a==-1 || a==-2){
            printText("нельзя добавить команду, так как найдено больше одного результата");
        }
        else {
            printText("готово");
        }
    }

    public void addAnimalCommandById() throws SQLException {
        printText("введите Id");
        String id = scanner.nextLine();
        printText("введите комманду");
        String command = scanner.nextLine();
        presenter.addCommandById(id,command);
    }

    public void autoSend(){
        String s="";
        if(!presenter.getAutoSend()){
            s="локальный режим, все изменения сохраняются локально";
        }
        else {
            s="режим автоотправки, все ваши изменения автоматически отправляются на сервер";
        }
        printText("уважаемы пользователь, у вас включен "+s+" хотите его поменять? 1(да)/2(нет)");
        s = scanner.nextLine();
        if(Objects.equals(s, "1")){
            presenter.autoSend(!presenter.getAutoSend());
            printText("режим изменен");
        }else if(Objects.equals(s, "2")) {
            printText("режим не изменен");
        }
        else {
            inputError();
        }
    }

    public void sendBackup() throws SQLException {
        printText("уважаемы пользователь, у вас есть следующие данные, готовые к отправке");
        printText(presenter.inputAllBackups());
        printText("отправить их на сервер? 1(да)/любое другое значение(нет)");
        String s = scanner.nextLine();
        if(Objects.equals(s, "1")) {
            presenter.sendBackup();
            printText("отправлены");
        }
    }
}
