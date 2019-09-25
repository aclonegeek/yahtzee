package com.yahtzee.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        this.turn = 1;

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
        if (this.rollsCounter >= 3) {
            return;
        } else if (diceToHoldString.isEmpty()) { // Re-roll everything!
            this.roll();
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

    @Override
    public String toString() {
        HashMap<ScoreType, Integer> scoreSheet = this.scoreSheet.getScoreSheet();

        return
            "---------------------------------------------------------------------------" +
            "\n" +
            "| Name: " + this.name.toString() +
            " | Current Score: " + this.getScore() +
            " | Current Round: " + this.turn +
            " |\n" +
            "--------------------------------------------------------------------------" +
            "\n" +
            "| (1) Ones: " + scoreSheet.get(ScoreType.ONES) +
            " | (2) Twos: " + scoreSheet.get(ScoreType.TWOS) +
            " | (3) Threes: " + scoreSheet.get(ScoreType.THREES) +
            " | (4) Fours: " + scoreSheet.get(ScoreType.FOURS) +
            " | (5) Fives: " + scoreSheet.get(ScoreType.FIVES) +
            " | (6) Sixes: " + scoreSheet.get(ScoreType.SIXES) +
            " | Bonus: " + scoreSheet.get(ScoreType.BONUS) +
            " |\n" +
            "--------------------------------------------------------------------------" +
            "\n" +
            "| (7) Large Straight: " + scoreSheet.get(ScoreType.LARGE_STRAIGHT) +
            " | (8) Small Straight: " + scoreSheet.get(ScoreType.SMALL_STRAIGHT) +
            " | (9) Full House: " + scoreSheet.get(ScoreType.FULL_HOUSE) +
            " | (10) Three of a Kind: " + scoreSheet.get(ScoreType.THREE_OF_A_KIND) +
            " |\n" +
            "--------------------------------------------------------------------------" +
            "\n" +
            "| (11) Four of a Kind: " + scoreSheet.get(ScoreType.FOUR_OF_A_KIND) +
            " | (12) Chance: " + scoreSheet.get(ScoreType.CHANCE) +
            " | (13) Yahtzee!: " + scoreSheet.get(ScoreType.YAHTZEE) +
            " |\n" +
            "--------------------------------------------------------------------------";
    }
}
