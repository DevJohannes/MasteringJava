package de.appiqstudios.cachesystem.commands;


import de.appiqstudios.cachesystem.core.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private final List<Command> availableCommands = new ArrayList<>();

    public CommandHandler(Server server, Socket socket) throws IOException {
        availableCommands.add(new InfoCommand(server, socket));
    }

    public void checkCommand(String input) {
        String[] split = input.split(" ");
        String commandName = split[0];
        String[] args = new String[split.length - 1];
        System.arraycopy(split, 1, args, 0, args.length);

        for (Command command : availableCommands) {
            if (command.getName().equals(commandName)) {
                command.execute(args);
                return;
            }
        }

        System.out.println("Unknown command: " + commandName);
    }

}
