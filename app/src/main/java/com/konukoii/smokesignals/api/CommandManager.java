package com.konukoii.smokesignals.api;

import android.content.Context;

import com.konukoii.smokesignals.R;

import java.util.HashMap;


/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class CommandManager {
    private HashMap<String, Command> commandMap;

    private static HashMap<String, Command> getCommands(Context context) {
        final String[] commands = context.getResources().getStringArray(R.array.commands_list);

        HashMap<String, Command> map = new HashMap<String, Command>();
        for(String command : commands) {
            Class<?> commandClass;
            try {
                commandClass = Class.forName("com.konukoii.smokesignals.api.commands." + command + "Command");
            } catch(ClassNotFoundException ex) {
                continue;
            }

            Command action;
            try {
                action = (Command) commandClass.newInstance();
            } catch(Exception ex) {
                continue;
            }

            map.put(command.toLowerCase(), action);
        }

        return map;
    }

    public CommandManager(Context context) {
        commandMap = getCommands(context);
    }

    public Command getCommand(String command) {
        Command temp = commandMap.get(command);
        if(temp == null) return getCommand("Help");
        else return temp;
    }
}
