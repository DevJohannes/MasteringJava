package de.appiqstudios.cachesystem;

import de.appiqstudios.cachesystem.core.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        server.start(2025);
    }
}
