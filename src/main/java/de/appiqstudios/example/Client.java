package de.appiqstudios.example.cachesystem;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Erstellt einen Socket, der eine Verbindung zum Server auf localhost:12345 herstellt
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Verbunden mit dem Server.");

            // Erstellt Input- und Output-Streams für die Kommunikation
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Sendet eine Nachricht an den Server
            out.println("Hallo vom Client!");

            // Liest die Antwort vom Server
            String serverMessage = in.readLine();
            System.out.println("Nachricht vom Server: " + serverMessage);

            // Schließt den Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}