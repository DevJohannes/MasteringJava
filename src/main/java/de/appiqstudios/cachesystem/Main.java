package de.appiqstudios.cachesystem;

import de.appiqstudios.cachesystem.core.builtin.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        server.start(2025);
    }
}
