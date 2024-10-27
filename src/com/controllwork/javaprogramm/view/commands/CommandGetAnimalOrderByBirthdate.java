package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetAnimalOrderByBirthdate extends CommandAbstract{
    public CommandGetAnimalOrderByBirthdate(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести по дате рождения";
    }

    public void execute() throws SQLException {
        consoleUI.getAnimalOrderByBirthdate();
    }
}
