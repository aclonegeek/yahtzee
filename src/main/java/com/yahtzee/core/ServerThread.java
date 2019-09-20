// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Server server;
    private Socket socket;

    private int playerIndex;

    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.playerIndex = server.getPlayers().size();
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));) {
            boolean isNewUser = true;
            out.println("Welcome player " + (this.playerIndex + 1) + ", please enter your name: ");

            boolean waiting = false;

            while (true) {
                if (waiting) {
                    continue;
                }

                String input = in.readLine();
                if (input == null) {
                    continue;
                }

                if (isNewUser) {
                    this.server.addPlayer(new Player(input));

                    if (!this.server.isGameActive()) { // TODO(randy): Probably remove this check.
                        if (this.server.getPlayers().size() == 1) {
                            out.println("Ready Player One? (y)");
                        } else {
                            out.println("Waiting for player 1 to start the game...");
                            waiting = true;
                        }
                    }

                    isNewUser = false;

                    continue;
                }

                if (!processInput(out, in, input)) {
                    break;
                }
            }

            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean processInput(PrintWriter out, BufferedReader in, String input) {
        switch (input) {
        case "y":
            this.server.setGameActive(true);
            break;
        case "q":
            this.server.setGameActive(false);
            return false;
        default:
            out.println("Invalid command \"" + input + "\"");
            break;
        }

        return true;
    }
}
