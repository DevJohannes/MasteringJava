package de.appiqstudios.cachesystem.network;

public interface Networker {
    void disconnect();
    void send();
    void isClosed();
}
