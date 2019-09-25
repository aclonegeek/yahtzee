// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<ServerThread> clients;
    private final ArrayList<Player> players;

    private int currentPlayer;
    private boolean gameActive;

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
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
        this.gameActive = false;
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for players to join...");

            while (true) {
                ServerThread serverThread = new ServerThread(this,
                                                             serverSocket.accept(),
                                                             this.clients.size());
                this.clients.add(serverThread);
                serverThread.start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }

    public void broadcastScoreboard() {
        this.clients.forEach(c -> c.outputScoreboard(this.players));
    }

    public void broadcastWinner(String name, int points) {
        this.clients.forEach(c -> c.outputWinner(name, points));
    }

    public boolean isGameActive() {
        return this.gameActive;
    }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
        this.broadcastScoreboard();
    }

    public void addPlayer(Player player) {
        System.out.println(player.getName() + " has entered the game lobby!");
        this.players.add(player);
    }

    public void nextPlayer() {
        System.out.println("Player " + (this.currentPlayer + 1) + " has completed their turn.");

        if (this.currentPlayer == 2) {
            int round = this.players.get(2).getTurn() - 1;
            System.out.println("Round " + round + " is complete.\n");

            // Game is done.
            if (round == 13) {
                determineWinner();
                return;
            }
        }

        this.broadcastScoreboard();

        switch (this.currentPlayer) {
        case 0:
            this.currentPlayer = 1;
            break;
        case 1:
            this.currentPlayer = 2;
            break;
        case 2:
            this.currentPlayer = 0;
            break;
        }
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    private void determineWinner() {
        String winnerName = "";
        int highestScore = 0;
        for (Player p : this.players) {
            if (p.getScore() > highestScore) {
                highestScore = p.getScore();
                winnerName = p.getName();
            }
        }

        this.broadcastWinner(winnerName, highestScore);
        System.out.println("Game Complete");
    }
}
