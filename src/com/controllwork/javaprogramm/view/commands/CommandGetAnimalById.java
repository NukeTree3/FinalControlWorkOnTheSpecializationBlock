package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandGetAnimalById extends CommandAbstract{
    public CommandGetAnimalById(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "Вывести животного по указанному ID";
    }

    public void execute() throws SQLException {
        consoleUI.getAnimalById();
    }
}
