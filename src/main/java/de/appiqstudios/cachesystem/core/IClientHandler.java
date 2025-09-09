package de.appiqstudios.cachesystem.core;

import de.appiqstudios.cachesystem.cache.IStorage;

import java.net.Socket;
import java.util.UUID;

/**
 * Represents a client handler that manages communication with a connected client.
 * This class implements Runnable to allow handling client connections in separate threads.
 **/

public abstract class IClientHandler implements Runnable {

    private Socket socket;
    private UUID uuid;
    private IStorage storage;

    public IClientHandler(Socket socket, UUID uuid, IStorage storage) {
        this.socket = socket;
        this.uuid = uuid;
        this.storage = storage;
    }

    @Override
    public void run() {

    }
}
