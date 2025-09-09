package de.appiqstudios.cachesystem.core.builtin;

import de.appiqstudios.cachesystem.cache.IStorage;
import de.appiqstudios.cachesystem.cache.builtin.Storage;
import de.appiqstudios.cachesystem.commands.CommandHandler;
import de.appiqstudios.cachesystem.core.IClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

/*
 * The builtin ClientHandler class handles communication with a connected client.
 * It extends the IClientHandler abstract class and implements the Runnable interface
 */
public class ClientHandler extends IClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final UUID clientId;
    private CommandHandler commandHandler;
    private final Server server;
    private IStorage storage;

    public ClientHandler(Server server, Socket socket, UUID clientId, Storage storage) throws IOException {
        super(socket, clientId, storage);
        this.socket = socket;
        this.clientId = clientId;
        this.server = server;
        this.storage = storage;

        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.commandHandler = new CommandHandler(server, socket, out, this);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;

            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Nachricht vom Client: " + clientMessage);
                out.println("Server hat die Nachricht erhalten: " + clientMessage);

                commandHandler.checkCommand(clientMessage);

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    System.out.println("Client hat die Verbindung beendet.");
                    break;
                }
            }

        } catch (IOException e) {
            try {
                // Closing the socket in case of an exception
                socket.close();
                throw new RuntimeException(e); // Rethrow as runtime exception
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public IStorage getStorage() {
        return storage;
    }
}