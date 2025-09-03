package de.appiqstudios.cachesystem.core;

import java.net.Socket;
import java.util.UUID;

public abstract class IClientHandler implements Runnable {

    private Socket socket;
    private UUID uuid;

    public IClientHandler(Socket socket, UUID uuid) {
        this.socket = socket;
        this.uuid = uuid;
    }

    @Override
    public void run() {

    }
}
