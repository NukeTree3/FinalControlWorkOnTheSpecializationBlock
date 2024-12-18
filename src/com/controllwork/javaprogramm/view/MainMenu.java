package com.controllwork.javaprogramm.view;

import com.controllwork.javaprogramm.view.commands.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<CommandAbstract> commandList;

    public MainMenu(ConsoleUI consoleUI){
        commandList = new ArrayList<>();
        commandList.add(new CommandGetCountOfAnimals(consoleUI));
        commandList.add(new CommandGetAllAnimals(consoleUI));
        commandList.add(new CommandGetAnimal(consoleUI));
        commandList.add(new CommandGetAnimalById(consoleUI));
        commandList.add(new CommandGetAnimalCommand(consoleUI));
        commandList.add(new CommandGetAnimalOrderByBirthdate(consoleUI));
        commandList.add(new CommandAddAnimal(consoleUI));
        commandList.add(new CommandAddAnimalCommand(consoleUI));
        commandList.add(new CommandAddCommandById(consoleUI));
        commandList.add(new CommandAutoSend(consoleUI));
        commandList.add(new CommandSendBackup(consoleUI));
        commandList.add(new CommandFinish(consoleUI));
    }

    public String outputMenuInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<commandList.size(); i++){
            stringBuilder.append((i+1) + " - ");
            stringBuilder.append(commandList.get(i).getDiscription() + "\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int i) throws IOException, ClassNotFoundException, SQLException {
        CommandAbstract command = commandList.get(i-1);
        command.execute();
    }

    public Integer getSize(){
        return commandList.size();
    }
}
