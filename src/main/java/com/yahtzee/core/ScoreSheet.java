package com.yahtzee.core;

import java.util.HashMap;

public class ScoreSheet {
    private HashMap<ScoreType, Integer> scoreSheet;

    public ScoreSheet() {
        this.scoreSheet = new HashMap<>();
        this.scoreSheet.put(ScoreType.ONES, 0);
        this.scoreSheet.put(ScoreType.TWOS, 0);
        this.scoreSheet.put(ScoreType.THREES, 0);
        this.scoreSheet.put(ScoreType.FOURS, 0);
        this.scoreSheet.put(ScoreType.FIVES, 0);
        this.scoreSheet.put(ScoreType.SIXES, 0);
        this.scoreSheet.put(ScoreType.BONUS, 0);

        this.scoreSheet.put(ScoreType.LARGE_STRAIGHT, 0);
        this.scoreSheet.put(ScoreType.SMALL_STRAIGHT, 0);
        this.scoreSheet.put(ScoreType.FULL_HOUSE, 0);
        this.scoreSheet.put(ScoreType.THREE_OF_A_KIND, 0);
        this.scoreSheet.put(ScoreType.FOUR_OF_A_KIND, 0);
        this.scoreSheet.put(ScoreType.CHANCE, 0);
        this.scoreSheet.put(ScoreType.YAHTZEE, 0);
    }

    public void score(ScoreType scoreType, Dice[] dice) {
        // Prevent scoring in an already scored in category.
        if (this.scoreSheet.get(scoreType) > 0) {
            return;
        }

        switch (scoreType) {
        case ONES:
        case TWOS:
        case THREES:
        case FOURS:
        case FIVES:
        case SIXES:
            this.scoreUpperSection(scoreType, dice);
        }
    }

    private void scoreUpperSection(ScoreType scoreType, Dice[] dice) {
        int count = 0;
        int section = scoreType.ordinal() + 1;

        for (Dice d : dice) {
            if (d.getValue() == section) {
                count++;
            }
        }

        this.scoreSheet.put(scoreType,
                            this.scoreSheet.get(scoreType) + (section * count));

        // Score the upper section bonus if:
        //     a. It is eligible to be scored, and
        //     b. It hasn't be scored yet.
        if (calculateUpperSectionScoreWithoutBonus() >= 63 &&
            this.scoreSheet.get(ScoreType.BONUS) == 0) {
            this.scoreSheet.put(ScoreType.BONUS, 35);
        }
    }

    private int calculateUpperSectionScoreWithoutBonus() {
        return this.scoreSheet.get(ScoreType.ONES) +
               this.scoreSheet.get(ScoreType.TWOS) +
               this.scoreSheet.get(ScoreType.THREES) +
               this.scoreSheet.get(ScoreType.FOURS) +
               this.scoreSheet.get(ScoreType.FIVES) +
               this.scoreSheet.get(ScoreType.SIXES);
    }

    public int calculateScore() {
        return this.scoreSheet.values()
                              .stream()
                              .mapToInt(Integer::intValue)
                              .sum();
    }

    public HashMap<ScoreType, Integer> getScoreSheet() {
        return this.scoreSheet;
    }
}
