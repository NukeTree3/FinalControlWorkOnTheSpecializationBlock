package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetAllAnimals extends CommandAbstract{
    public CommandGetAllAnimals(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести всех животных";
    }

    public void execute() throws SQLException {
        consoleUI.outputAllAnimals();
    }
}
