package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandAddCommandById extends CommandAbstract{
    public CommandAddCommandById(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "добавление новой команды черерз id";
    }

    public void execute() throws SQLException {
        consoleUI.addAnimalCommandById();
    }
}