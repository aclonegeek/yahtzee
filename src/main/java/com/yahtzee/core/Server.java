// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private ArrayList<ServerThread> clients;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Specify a port number");
            System.exit(-1);
        }

        int port = Integer.parseInt(args[0]);

        Server server = new Server();
        server.start(port);
    }

    public Server() {
        this.clients = new ArrayList<>();
    }

    public void start(int port) {
        System.out.println("Waiting for players to join...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                ServerThread serverThread = new ServerThread(serverSocket.accept());
                this.clients.add(serverThread);
                serverThread.start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
