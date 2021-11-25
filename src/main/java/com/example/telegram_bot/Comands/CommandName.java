package com.example.telegram_bot.Comands;

public enum CommandName {


    START("/start"),
    HELP("/help"),
    NO_COMMAND("nocommand"),
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
