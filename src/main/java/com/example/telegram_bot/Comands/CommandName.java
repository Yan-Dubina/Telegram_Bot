package com.example.telegram_bot.Comands;

public enum CommandName {


    START("/start"),
    HELP("/help"),
    NO_COMMAND("nocommand"),
    NEW_USER("/adduser"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub"),
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
