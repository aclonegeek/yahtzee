package com.yahtzee.core;

import io.cucumber.java8.En;

import static org.junit.Assert.*;

import java.util.HashMap;

public class Stepdefs implements En {
    private Player player;
    private HashMap<ScoreType, Integer> scoreSheet;

    private ScoreType createScoreTypeFromString(final String scoreType) {
        switch (scoreType) {
        case "Aces":
            return ScoreType.ONES;
        case "Twos":
            return ScoreType.TWOS;
        case "Threes":
            return ScoreType.THREES;
        case "Fours":
            return ScoreType.FOURS;
        case "Fives":
            return ScoreType.FIVES;
        case "Sixes":
            return ScoreType.SIXES;
        case "Large straight":
            return ScoreType.LARGE_STRAIGHT;
        case "Small straight":
            return ScoreType.SMALL_STRAIGHT;
        case "Full House":
            return ScoreType.FULL_HOUSE;
        case "Three of a Kind":
            return ScoreType.THREE_OF_A_KIND;
        case "Four of a Kind":
            return ScoreType.FOUR_OF_A_KIND;
        case "Chance":
            return ScoreType.CHANCE;
        case "Yahtzee":
            return ScoreType.YAHTZEE;
        case "Bonus":
            return ScoreType.BONUS;
        }

        return null;
    }

    public Stepdefs() {
        this.player = new Player("Cucumber");
        this.scoreSheet = this.player.getScoreSheet().getScoreSheet();

        // Scoring Upper & Lower section (no bonuses).
        Given("I have rolled the dice {string}", (final String dice) -> {
                this.player.setDice(dice);
            });
        When("I score my roll in the {string} section", (final String scoreTypeString) -> {
                ScoreType scoreType = createScoreTypeFromString(scoreTypeString);
                this.player.score(scoreType);
            });
        Then("the {string} section should be {int}", (final String scoreTypeString, final Integer score) -> {
                assertEquals(score,
                             this.scoreSheet.get(createScoreTypeFromString(scoreTypeString)));
            });

        // Scoring Upper Section bonus.
        Given("I have a score of {int} in the upper section", (final Integer upperSectionScore) -> {
                if (upperSectionScore == 63) {
                    this.player.setDice("4 4 1 1 1");
                    this.player.score(ScoreType.FOURS);
                    this.player.setDice("5 5 5 5 5");
                    this.player.score(ScoreType.FIVES);
                    this.player.setDice("6 6 6 6 6");
                    this.player.score(ScoreType.SIXES);
                } else if (upperSectionScore == 69) {
                    this.player.setDice("2 1 1 1 1");
                    this.player.score(ScoreType.TWOS);
                    this.player.setDice("4 4 4 2 1");
                    this.player.score(ScoreType.FOURS);
                    this.player.setDice("5 5 5 5 5");
                    this.player.score(ScoreType.FIVES);
                    this.player.setDice("6 6 6 6 6");
                    this.player.score(ScoreType.SIXES);
                }

                assertEquals(upperSectionScore,
                             Integer.valueOf(this.player
                                                 .getScoreSheet()
                                                 .calculateUpperSectionScoreWithoutBonus()));
            });
        Then("the upper section bonus should be {int}", (final Integer score) -> {
                assertEquals(score,
                             this.scoreSheet.get(ScoreType.BONUS));
            });
    }
}
