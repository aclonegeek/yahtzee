package com.yahtzee.core;

import java.util.Arrays;
import java.util.HashMap;

public class ScoreSheet {
    private HashMap<ScoreType, Integer> scoreSheet;

    private boolean scoringYahtzeeBonus;

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

        this.scoringYahtzeeBonus = false;
    }

    public void score(ScoreType scoreType, Dice[] dice) {
        // Prevent scoring in an already scored in category, except for yahtzee.
        if (scoreType != ScoreType.YAHTZEE && this.scoreSheet.get(scoreType) > 0) {
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
        case YAHTZEE:
            this.scoreYahtzee(dice);
            return;
        case CHANCE:
            this.scoreChance(dice);
            break;
        default:
            break;
        }

        if (this.scoringYahtzeeBonus) {
            this.scoringYahtzeeBonus = false;
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
            this.scoreSheet.put(ScoreType.BONUS, Globals.UPPER_SECTION_BONUS_SCORE);
        }
    }

    private void scoreThreeOrFourOfAKind(final ScoreType scoreType, final Dice[] dice) {
        final int countDesired = scoreType == ScoreType.THREE_OF_A_KIND ? 3 : 4;
        int[] occurences = new int[] {0, 0, 0, 0, 0, 0};

        for (Dice d : dice) {
            occurences[d.getValue() - 1]++;
        }

        if (!Arrays.stream(occurences).anyMatch(v -> v == countDesired)) {
            return;
        }

        int score = 0;
        for (Dice d : dice) {
            score += d.getValue();
        }
        this.scoreSheet.put(scoreType, score);
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
            this.scoreSheet.put(ScoreType.FULL_HOUSE, Globals.FULL_HOUSE_SCORE);
        }
    }

    private void scoreStraight(ScoreType scoreType, Dice[] dice) {
        int amountOfSequentialNumbers = scoreType == ScoreType.SMALL_STRAIGHT ? 4 : 5;
        int[] sortedDice = Arrays.stream(dice)
                                 .mapToInt(v -> v.getValue())
                                 .sorted()
                                 .toArray();

        for (int i = 0; i < sortedDice.length - 1; i++) {
            // We already have a small straight, no need to do extra work.
            if (amountOfSequentialNumbers == 4 && i == 4) {
                break;
            }

            if (sortedDice[i] >= sortedDice[i + 1]) {
                // We may still have a small straight after the first item.
                if (amountOfSequentialNumbers == 4 && i == 0) {
                    continue;
                }

                return;
            }
        }

        this.scoreSheet.put(scoreType,
                            scoreType == ScoreType.SMALL_STRAIGHT ?
                            Globals.SMALL_STRAIGHT_SCORE : Globals.LARGE_STRAIGHT_SCORE);
    }

    private void scoreYahtzee(Dice[] dice) {
        if (!Arrays.asList(dice)
                   .stream()
                   .allMatch(v -> v.getValue() == dice[0].getValue())) {
            return;
        }

        if (this.scoreSheet.get(ScoreType.YAHTZEE) == 0) { // First Yahtzee!
            this.scoreSheet.put(ScoreType.YAHTZEE, Globals.YAHTZEE_SCORE);
        } else {                                           // Bonus Yahtzee!
            this.scoreSheet.put(ScoreType.YAHTZEE,
                                this.scoreSheet.get(ScoreType.YAHTZEE) + Globals.YAHTZEE_BONUS_SCORE);

            int value = dice[0].getValue();
            ScoreType scoreType = ScoreType.values()[value - 1];

            // Score the corresponding upper section if it isn't already scored.
            if (this.scoreSheet.get(scoreType) == 0) {
                this.scoreSheet.put(scoreType, value * 5);
            } else {
                this.scoringYahtzeeBonus = true;
            }
        }
    }

    private void scoreChance(Dice[] dice) {
        int value = 0;
        for (Dice d : dice) {
            value += d.getValue();
        }
        this.scoreSheet.put(ScoreType.CHANCE, value);
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

    public boolean canScoreYahtzeeBonus() {
        return this.scoringYahtzeeBonus;
    }

    public HashMap<ScoreType, Integer> getScoreSheet() {
        return this.scoreSheet;
    }
}
