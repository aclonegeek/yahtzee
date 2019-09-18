// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Specify an IP and port number");
            System.exit(-1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String serverMessage = in.readLine();
                if (serverMessage != null) {
                    System.out.println(serverMessage);
                }

                String userInput = stdIn.readLine();
                if (userInput != null) {
                    out.println(userInput);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to connect to " + host);
            System.exit(-1);
        }
    }
}
