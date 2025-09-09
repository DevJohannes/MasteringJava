package de.appiqstudios.cachesystem.commands;


import de.appiqstudios.cachesystem.commands.builtin.GetCommand;
import de.appiqstudios.cachesystem.commands.builtin.InfoCommand;
import de.appiqstudios.cachesystem.commands.builtin.SetCommand;
import de.appiqstudios.cachesystem.core.builtin.ClientHandler;
import de.appiqstudios.cachesystem.core.builtin.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private final List<Command> availableCommands = new ArrayList<>();
    private final PrintWriter out;
    private final ClientHandler handler;

    public CommandHandler(Server server, Socket socket, PrintWriter out, ClientHandler handler) throws IOException {
        this.out = out;
        this.handler = handler;

        availableCommands.add(new InfoCommand(server, socket, out));
        availableCommands.add(new SetCommand(server, socket, out, handler.getStorage()));
        availableCommands.add(new GetCommand(server, socket, out, handler.getStorage()));
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

        out.println("Unknown command: " + commandName);
    }

}
