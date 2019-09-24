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
            break;
        case THREE_OF_A_KIND:
        case FOUR_OF_A_KIND:
            this.scoreThreeOrFourOfAKind(scoreType, dice);
            break;
        case FULL_HOUSE:
            this.scoreFullHouse(dice);
            break;
        case SMALL_STRAIGHT:
        case LARGE_STRAIGHT:
            this.scoreStraight(scoreType, dice);
            break;
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

        this.scoreSheet.put(scoreType, section * count);

        // Score the upper section bonus if:
        //     a. It is eligible to be scored, and
        //     b. It hasn't be scored yet.
        if (calculateUpperSectionScoreWithoutBonus() >= 63 &&
            this.scoreSheet.get(ScoreType.BONUS) == 0) {
            this.scoreSheet.put(ScoreType.BONUS, 35);
        }
    }

    private void scoreThreeOrFourOfAKind(ScoreType scoreType, Dice[] dice) {
        int countDesired = scoreType == ScoreType.THREE_OF_A_KIND ? 3 : 4;
        int[] occurences = new int[] {0, 0, 0, 0, 0, 0};

        for (Dice d : dice) {
            occurences[d.getValue() - 1]++;
        }

        for (int i = 0; i < occurences.length; i++) {
            if (occurences[i] == countDesired) {
                this.scoreSheet.put(scoreType, countDesired * (i + 1));
                return;
            }
        }
    }

    private void scoreFullHouse(Dice[] dice) {
        int[] occurences = new int[] {0, 0, 0, 0, 0, 0};
        boolean twoDiceSame = false;
        boolean threeDiceSame = false;

        for (Dice d : dice) {
            occurences[d.getValue() - 1]++;
        }

        for (int i = 0; i < occurences.length; i++) {
            if (occurences[i] == 2) {
                twoDiceSame = true;
            } else if (occurences[i] == 3) {
                threeDiceSame = true;
            }
        }

        if (twoDiceSame && threeDiceSame) {
            this.scoreSheet.put(ScoreType.FULL_HOUSE, 25);
        }
    }

    private void scoreStraight(ScoreType scoreType, Dice[] dice) {
        int amountOfSequentialNumbers = scoreType == ScoreType.SMALL_STRAIGHT ? 4 : 5;
        boolean canStillBeScored = true;

        for (int i = 0; i < dice.length - 1; i++) {
            if (dice[i].getValue() > dice[i + 1].getValue()) {
                if (amountOfSequentialNumbers == 5) {
                    return;
                }

                // A small straight can still potentially be scored.
                if (i == 0) {
                    canStillBeScored = false;
                    continue;
                } else if (i == dice.length - 2 && canStillBeScored) {
                    continue;
                }

                return;
            }
        }

        this.scoreSheet.put(scoreType,
                            scoreType == ScoreType.SMALL_STRAIGHT ? 30 : 40);
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
