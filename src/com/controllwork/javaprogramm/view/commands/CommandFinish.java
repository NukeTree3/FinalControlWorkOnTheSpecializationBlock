package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.io.IOException;
import java.sql.SQLException;

public class CommandFinish extends CommandAbstract{
    public CommandFinish(ConsoleUI consoleUI){
        super(consoleUI);
        discription = "завершение работы";
    }

    public void execute() throws IOException {
        consoleUI.finish();
    }
}
