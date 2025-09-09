package de.appiqstudios.cachesystem.commands.builtin;

import de.appiqstudios.cachesystem.cache.IStorage;
import de.appiqstudios.cachesystem.commands.Command;
import de.appiqstudios.cachesystem.core.builtin.Server;

import java.io.PrintWriter;
import java.net.Socket;

public class GetCommand extends Command {

    private final Server server;
    private final Socket socket;
    private final PrintWriter out;
    private final IStorage storage;

    public GetCommand(Server server, Socket socket, PrintWriter out, IStorage storage) {
        super("GET", server, socket, out);

        this.server = server;
        this.socket = socket;
        this.out = out;
        this.storage = storage;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            out.println("Usage: GET <key>");
            return;
        }

        String value = storage.get(args[0]);

        if (value == null) {
            out.println("This key does not exist!");
            return;
        }

        out.println(value);



    }
}
