package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.sql.SQLException;

public class CommandSendBackup extends CommandAbstract{
    public CommandSendBackup(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "отправка всех локальных изменений";
    }

    public void execute() throws SQLException {
        consoleUI.sendBackup();
    }
}
