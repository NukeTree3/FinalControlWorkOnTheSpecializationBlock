package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetCountOfAnimals extends CommandAbstract{
    public CommandGetCountOfAnimals(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести общее количество животных";
    }

    public void execute() throws SQLException {
        consoleUI.getCount();
    }
}
