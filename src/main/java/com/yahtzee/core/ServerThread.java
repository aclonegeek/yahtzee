// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private enum GameState {
        WAITING_ON_ROLL,
        POST_ROLL_ACTION,
        HOLD_AND_REROLL,
        SCORE,
    };
    private GameState gameState;

    private final Server server;
    private final Socket socket;

    private final PrintWriter out;

    private Player player;
    private final int playerIndex;

    public ServerThread(Server server, Socket socket, int index) throws IOException {
        this.server = server;
        this.socket = socket;

        this.out = new PrintWriter(socket.getOutputStream(), true);

        this.playerIndex = index;
        this.gameState = GameState.WAITING_ON_ROLL;
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

                this.player = new Player(input);
                this.server.addPlayer(this.player);

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
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    continue;
                }

                this.outputPrompt();

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
        switch (this.gameState) {
        case WAITING_ON_ROLL:
            switch (input) {
            case "": // Enter.
                this.roll();
                this.gameState = GameState.POST_ROLL_ACTION;
                break;
            }
            break;
        case POST_ROLL_ACTION:
            switch (input) {
            case "1":
                this.gameState = GameState.HOLD_AND_REROLL;
                break;
            case "2":
                this.reroll("");
                if (this.player.getNumberOfRolls() >= 3) {
                    this.gameState = GameState.SCORE;
                } else {
                    this.gameState = GameState.POST_ROLL_ACTION;
                }
                break;
            case "3":
                this.gameState = GameState.SCORE;
                break;
            default:
                this.out.println("Invalid command \"" + input + "\"");
                break;
            }
            break;
        case HOLD_AND_REROLL:
            this.reroll(input);
            if (this.player.getNumberOfRolls() >= 3) {
                this.gameState = GameState.SCORE;
            } else {
                this.gameState = GameState.POST_ROLL_ACTION;
            }
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
                out.println(players.get(0));
            }
        }
    }

    private void roll() {
        Dice[] dice = this.player.getDice();
        this.player.roll();
        this.outputRoll(dice);
    }

    private void reroll(String diceToHold) {
        Dice[] dice = this.player.getDice();
        this.player.reroll(diceToHold);
        this.outputRoll(dice);
    }

    private void outputRoll(Dice[] dice) {
        this.out.println("              -----   -----   -----   -----   -----");
        this.out.println("You rolled:   " +
                         "| " + dice[0].getValue() + " |   " +
                         "| " + dice[1].getValue() + " |   " +
                         "| " + dice[2].getValue() + " |   " +
                         "| " + dice[3].getValue() + " |   " +
                         "| " + dice[4].getValue() + " |   "
                         );
        this.out.println("              -----   -----   -----   -----   -----");
    }

    private void outputPrompt() {
        switch (this.gameState) {
        case WAITING_ON_ROLL:
            this.out.println("Press <<ENTER>> to roll the dice...");
            break;
        case POST_ROLL_ACTION:
            this.out.println("What action would you like to perform next?");
            this.out.println(" (1) Select dice to hold, and then re-roll the other dice?");
            this.out.println(" (2) Re-roll all the dice?");
            this.out.println(" (3) Score this round?");
            break;
        case HOLD_AND_REROLL:
            this.out.println("Please enter in the dice position that you want to hold. " +
                             "Please seperate each number with a <<SPACE>>:");
            break;
        case SCORE:
            this.out.println("What category do you want to score this round against? " +
                             "(Please enter the category number)");
            break;
        default:
            break;
        }
    }
}
