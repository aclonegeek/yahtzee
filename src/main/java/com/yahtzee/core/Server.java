// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Specify a port number");
            System.exit(-1);
        }

        int port = Integer.parseInt(args[0]);

        System.out.println("Waiting for players to join...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
