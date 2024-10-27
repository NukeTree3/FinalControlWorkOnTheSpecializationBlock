package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetAnimal extends CommandAbstract{
    public CommandGetAnimal(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести животного по указанному имени";
    }

    public void execute() throws SQLException {
        consoleUI.getAnimal();
    }
}
