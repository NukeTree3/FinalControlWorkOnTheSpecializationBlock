package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetAnimalCommand extends CommandAbstract{
    public CommandGetAnimalCommand(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести команды животного";
    }

    public void execute() throws SQLException {
        consoleUI.getAnimalCommand();
    }
}
