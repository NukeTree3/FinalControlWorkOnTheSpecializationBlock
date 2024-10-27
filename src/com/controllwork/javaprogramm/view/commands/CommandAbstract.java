package com.controllwork.javaprogramm.view.commands;

import com.controllwork.javaprogramm.view.ConsoleUI;

import java.io.IOException;
import java.sql.SQLException;

public class CommandAbstract {
    String discription;
    ConsoleUI consoleUI;

    public CommandAbstract(ConsoleUI consoleUI){
        this.consoleUI = consoleUI;
    }

    public String getDiscription(){
        return discription;
    }

    public void execute() throws IOException, ClassNotFoundException, SQLException {

    }
}
