package com.example.telegram_bot.Commands;

public enum CommandName {


    START("/start"),
    HELP("/help"),
    NO_COMMAND("nocommand"),
    NEW_USER("/adduser"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub"),
    DELETE_GROUP_SUB("/deletegroupsub"),
    STOP ("/stop");

    private final String commandName;
    CommandName(String commandName)
    {
        this.commandName=commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
