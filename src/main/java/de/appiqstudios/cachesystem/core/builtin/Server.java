package de.appiqstudios.cachesystem.core.builtin;

import de.appiqstudios.cachesystem.cache.builtin.Storage;
import de.appiqstudios.cachesystem.core.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

/*
* The Server class sets up a server socket to listen for incoming client connections.
* For each connected client, it starts a new IClientHandler thread to manage communication.
* It implements the IServer interface to provide the functionality of the server.
*/
public class Server implements IServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private HashMap<UUID, Socket> connectedClients = new HashMap<>();

    public Server() {

    }

    public void start(Integer port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server gestartet und wartet auf Verbindungen...");

            while (true) {
                UUID clientId = UUID.randomUUID();
                clientSocket = serverSocket.accept();
                connectedClients.put(clientId, clientSocket);

                System.out.println("Neuer Client verbunden: " + clientSocket.getInetAddress().getHostAddress() + " mit ID: " + clientId);

                // Starting a new thread for each connected client after their connection is accepted
                new Thread(new ClientHandler(this, clientSocket, clientId, new Storage())).start();
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {

    }

    public HashMap<UUID, Socket> getConnectedClients() {
        return connectedClients;
    }
}
