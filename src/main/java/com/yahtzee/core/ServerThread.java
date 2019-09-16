// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        System.out.println("Client connected");
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));) {
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    break;
                }
            }

            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
