package de.appiqstudios.cachesystem.commands.builtin;

import de.appiqstudios.cachesystem.commands.Command;
import de.appiqstudios.cachesystem.core.builtin.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class InfoCommand extends Command {
    private final Server server;
    private final Socket socket;
    private final PrintWriter out;

    public InfoCommand(Server server, Socket socket, PrintWriter out) throws IOException {
        super("INFO", server, socket, out);
        this.server = server;
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void execute(String[] args) {
        out.println("Server is running. Active connections: " + server.getConnectedClients());
    }
}
