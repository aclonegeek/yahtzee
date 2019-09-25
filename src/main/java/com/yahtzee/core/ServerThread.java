// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private final Server server;
    private final Socket socket;

    private final PrintWriter out;

    private final int playerIndex;

    public ServerThread(Server server, Socket socket, int index) throws IOException {
        this.server = server;
        this.socket = socket;

        this.out = new PrintWriter(socket.getOutputStream(), true);

        this.playerIndex = index;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));) {
            out.println("Welcome player " + (this.playerIndex + 1) + ", please enter your name: ");

            String input;

            while (true) {
                input = in.readLine();
                if (input == null) {
                    continue;
                }

                this.server.addPlayer(new Player(input));

                if (this.playerIndex == 0) {
                    out.println("Ready Player One? (y)");

                    while (true) {
                        input = in.readLine();
                        if (input == null) {
                            continue;
                        }

                        if (input.equals("y")) {
                            System.out.println("The game is starting!");
                            this.server.setGameActive(true);
                            break;
                        }
                    }
                } else {
                    out.println("Waiting for player 1 to start the game...");
                }

                break;
            }

            while (true) {
                if (!this.server.isGameActive() ||
                    this.server.getCurrentPlayer() != this.playerIndex) {
                    continue;
                }

                this.out.println("Press <<ENTER>> to roll the dice...");

                input = in.readLine();
                if (input == null) {
                    continue;
                }

                if (!processInput(in, input)) {
                    break;
                }
            }

            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean processInput(BufferedReader in, String input) {
        switch (input) {
        default:
            this.out.println("Invalid command \"" + input + "\"");
            break;
        }

        return true;
    }

    public void outputScoreboard(ArrayList<Player> players) {
        out.println(players.get(this.playerIndex));
        out.println();

        if (this.playerIndex == 0) {
            out.println(players.get(1));
            out.println();

            if (players.size() == 3) {
                out.println(players.get(2));
            }
        } else if (this.playerIndex == 1) {
            out.println(players.get(0));
            out.println();

            if (players.size() == 3) {
                out.println(players.get(2));
            }
        } else if (this.playerIndex == 2) {
            out.println(players.get(1));
            out.println();

            if (players.size() == 3) {
                out.println(players.get(2));
            }
        }
    }
}
