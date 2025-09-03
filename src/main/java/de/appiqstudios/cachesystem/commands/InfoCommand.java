package de.appiqstudios.cachesystem.commands;

import de.appiqstudios.cachesystem.core.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class InfoCommand extends Command {
    private final Server server;
    private final Socket socket;

    private PrintWriter out;

    public InfoCommand(Server server, Socket socket) throws IOException {
        super("INFO", socket);
        this.server = server;
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void execute(String[] args) {
        out.println("Server is running. Active connections: " + server.getConnectedClients());
    }
}
