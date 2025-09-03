package de.appiqstudios.cachesystem.core;

import de.appiqstudios.cachesystem.commands.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

/*
* The ClientHandler class handles communication with a connected client.
* It implements Runnable to allow handling each client in a separate thread.
*/
public class ClientHandler extends IClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final UUID clientId;
    private CommandHandler commandHandler;
    private final Server server;

    public ClientHandler(Server server, Socket socket, UUID clientId) throws IOException {
        super(socket, clientId);
        this.socket = socket;
        this.clientId = clientId;
        this.server = server;

        this.commandHandler = new CommandHandler(server, socket);
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

}
