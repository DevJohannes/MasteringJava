package de.appiqstudios.cachesystem.commands;

import de.appiqstudios.cachesystem.core.builtin.Server;

import java.io.PrintWriter;
import java.net.Socket;

public abstract class Command {
    private final String name;
    private final Socket socket;

    public Command(String name, Server server, Socket socket, PrintWriter out) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void execute(String[] args) {
        // TODO
    }

}
