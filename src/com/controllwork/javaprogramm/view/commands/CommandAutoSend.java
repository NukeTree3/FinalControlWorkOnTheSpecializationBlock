package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandAutoSend extends CommandAbstract{
    public CommandAutoSend(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "изменение режима работы";
    }

    public void execute() throws SQLException {
        consoleUI.autoSend();
    }
}
