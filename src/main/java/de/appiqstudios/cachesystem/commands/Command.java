package de.appiqstudios.cachesystem.commands;

import java.net.Socket;

public abstract class Command {
    private final String name;
    private final Socket socket;

    public Command(String name, Socket socket) {
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
