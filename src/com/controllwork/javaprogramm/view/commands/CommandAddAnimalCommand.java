package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandAddAnimalCommand extends CommandAbstract{
    public CommandAddAnimalCommand(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "добваление новой команды";
    }

    public void execute() throws SQLException {
        consoleUI.addAnimalCommand();
    }
}
