package de.appiqstudios.cachesystem.commands.builtin;


/*
* Command for setting a key-value pair in the cache
* Usage: SET <key> <value>
*/

import de.appiqstudios.cachesystem.cache.IStorage;
import de.appiqstudios.cachesystem.commands.Command;
import de.appiqstudios.cachesystem.core.builtin.Server;

import java.io.PrintWriter;
import java.net.Socket;

public class SetCommand extends Command {

    private final Server server;
    private final Socket socket;
    private final PrintWriter out;
    private final IStorage storage;

    public SetCommand(Server server, Socket socket, PrintWriter out, IStorage storage) {
        super("SET", server, socket, out);

        this.server = server;
        this.socket = socket;
        this.storage = storage;
        this.out = out;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: SET <key> <value>");
            return;
        }

        String key = args[0];
        String value = args[1];

        out.println(key);
        out.println(value);

        storage.set(key, value);
    }
}
