package com.yahtzee.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;

    private final Dice[] dice;

    private final ScoreSheet scoreSheet;

    private int rollsCounter;
    private int turn;

    public Player(String name) {
        this.name = name;

        this.dice = new Dice[5];
        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i] = new Dice();
        }

        this.rollsCounter = 0;
        this.turn = 0;

        this.scoreSheet = new ScoreSheet();
    }

    public void roll() {
        for (Dice d : this.dice) {
            d.roll();
        }

        this.rollsCounter++;
    }

    // Format: Numbers separated by spaces (i.e. 2 3 4 5).
    public void reroll(String diceToHoldString) {
        if (this.rollsCounter >= 3 || diceToHoldString.isEmpty()) {
            return;
        }

        List<Integer> diceToHold = Arrays.stream(diceToHoldString.split(" "))
                                         .mapToInt(Integer::parseInt)
                                         .boxed()
                                         .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < this.dice.length; i++) {
            if (diceToHold.contains(i + 1)) {
                continue;
            }

            this.dice[i].roll();
        }

        this.rollsCounter++;
    }

    public void score(ScoreType scoreType) {
        this.scoreSheet.score(scoreType, this.dice);
    }

    public void finishTurn() {
        this.rollsCounter = 0;
        this.turn++;
    }

    public String getName() {
        return this.name;
    }

    public Dice[] getDice() {
        return this.dice;
    }

    public ScoreSheet getScoreSheet() {
        return this.scoreSheet;
    }

    public int getScore() {
        return this.scoreSheet.calculateScore();
    }

    public int getNumberOfRolls() {
        return this.rollsCounter;
    }
}
