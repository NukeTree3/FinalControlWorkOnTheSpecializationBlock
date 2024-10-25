package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandAddAnimal extends CommandAbstract{
    public CommandAddAnimal(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "добавление нового животного";
    }

    public void execute() throws SQLException {
        consoleUI.addAnimal();
    }
}