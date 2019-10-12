package com.yahtzee.core;

import junit.framework.TestCase;

public class ScoreSheetTest extends TestCase {
    private Dice[] createDiceArray(int a, int b, int c, int d, int e) {
        return new Dice[] { new Dice(a),
                            new Dice(b),
                            new Dice(c),
                            new Dice(d),
                            new Dice(e) };
    }

    public void testScoringAllOnes() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 1, 1, 1, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.ONES, dice);

        assertEquals(Integer.valueOf(5),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(5, ss.calculateScore());
    }

    public void testScoringThreeOnes() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 2, 1, 3, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.ONES, dice);

        assertEquals(Integer.valueOf(3),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(3, ss.calculateScore());
    }

    public void testScoringAllTwos() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(2, 2, 2, 2, 2);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.TWOS));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.TWOS, dice);

        assertEquals(Integer.valueOf(10),
                     ss.getScoreSheet().get(ScoreType.TWOS));
        assertEquals(10, ss.calculateScore());
    }

    public void testScoringThreeTwos() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(2, 1, 2, 3, 2);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.TWOS));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.TWOS, dice);

        assertEquals(Integer.valueOf(6),
                     ss.getScoreSheet().get(ScoreType.TWOS));
        assertEquals(6, ss.calculateScore());
    }

    public void testScoringAllThrees() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(3, 3, 3, 3, 3);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.THREES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.THREES, dice);

        assertEquals(Integer.valueOf(15),
                     ss.getScoreSheet().get(ScoreType.THREES));
        assertEquals(15, ss.calculateScore());
    }

    public void testScoringThreeThrees() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(3, 1, 3, 2, 3);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.THREES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.THREES, dice);

        assertEquals(Integer.valueOf(9),
                     ss.getScoreSheet().get(ScoreType.THREES));
        assertEquals(9, ss.calculateScore());
    }

    public void testScoringAllFours() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(4, 4, 4, 4, 4);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FOURS));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FOURS, dice);

        assertEquals(Integer.valueOf(20),
                     ss.getScoreSheet().get(ScoreType.FOURS));
        assertEquals(20, ss.calculateScore());
    }

    public void testScoringThreeFours() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(4, 1, 4, 2, 4);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FOURS));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FOURS, dice);

        assertEquals(Integer.valueOf(12),
                     ss.getScoreSheet().get(ScoreType.FOURS));
        assertEquals(12, ss.calculateScore());
    }

    public void testScoringAllFives() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(5, 5, 5, 5, 5);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FIVES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FIVES, dice);

        assertEquals(Integer.valueOf(25),
                     ss.getScoreSheet().get(ScoreType.FIVES));
        assertEquals(25, ss.calculateScore());
    }

    public void testScoringThreeFives() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(5, 1, 5, 2, 5);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FIVES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FIVES, dice);

        assertEquals(Integer.valueOf(15),
                     ss.getScoreSheet().get(ScoreType.FIVES));
        assertEquals(15, ss.calculateScore());
    }

    public void testScoringAllSixes() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SIXES, dice);

        assertEquals(Integer.valueOf(30),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(30, ss.calculateScore());
    }

    public void testScoringThreeSixes() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 6, 2, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SIXES, dice);

        assertEquals(Integer.valueOf(18),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(18, ss.calculateScore());
    }

    public void testScoringUpperSectionBonusAtExactly63() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);
        ss.score(ScoreType.SIXES, dice);

        dice = createDiceArray(5, 5, 5, 5, 5);
        ss.score(ScoreType.FIVES, dice);

        dice = createDiceArray(2, 2, 2, 2, 1);
        ss.score(ScoreType.TWOS, dice);

        assertEquals(Integer.valueOf(Globals.UPPER_SECTION_BONUS_SCORE),
                     ss.getScoreSheet().get(ScoreType.BONUS));
        assertEquals(63 + Globals.UPPER_SECTION_BONUS_SCORE,
                     ss.calculateScore());
    }

    public void testScoringUpperSectionBonusAbove63() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);
        ss.score(ScoreType.SIXES, dice);

        dice = createDiceArray(5, 5, 5, 5, 5);
        ss.score(ScoreType.FIVES, dice);

        dice = createDiceArray(4, 4, 4, 4, 4);
        ss.score(ScoreType.FOURS, dice);

        assertEquals(Integer.valueOf(Globals.UPPER_SECTION_BONUS_SCORE),
                     ss.getScoreSheet().get(ScoreType.BONUS));
        assertEquals((6 * 5) + (5 * 5) + (4 * 5) + Globals.UPPER_SECTION_BONUS_SCORE,
                     ss.calculateScore());
    }

    public void testScoringThreeOfAKind() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 6, 2, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.THREE_OF_A_KIND));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.THREE_OF_A_KIND, dice);

        assertEquals(Integer.valueOf(21),
                     ss.getScoreSheet().get(ScoreType.THREE_OF_A_KIND));
        assertEquals(21, ss.calculateScore());
    }

    public void testNotScoringThreeOfAKind() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 3, 2, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.THREE_OF_A_KIND));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.THREE_OF_A_KIND, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.THREE_OF_A_KIND));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringFourOfAKind() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 6, 6, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FOUR_OF_A_KIND));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FOUR_OF_A_KIND, dice);

        assertEquals(Integer.valueOf(25),
                     ss.getScoreSheet().get(ScoreType.FOUR_OF_A_KIND));
        assertEquals(25, ss.calculateScore());
    }

    public void testNotScoringFourOfAKind() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(2, 1, 6, 6, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FOUR_OF_A_KIND));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FOUR_OF_A_KIND, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FOUR_OF_A_KIND));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringFullHouse() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 6, 6, 6, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FULL_HOUSE, dice);

        assertEquals(Integer.valueOf(Globals.FULL_HOUSE_SCORE),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(25, ss.calculateScore());
    }

    public void testNotScoringFullHouseWithMissingDoubles() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 4, 5);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FULL_HOUSE, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(0, ss.calculateScore());
    }

    public void testNotScoringFullHouseWithMissingTriplets() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 1, 6, 1, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.FULL_HOUSE, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.FULL_HOUSE));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringSmallStraight() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 2, 3, 5);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SMALL_STRAIGHT, dice);

        assertEquals(Integer.valueOf(Globals.SMALL_STRAIGHT_SCORE),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(30, ss.calculateScore());
    }

    public void testScoringSmallStraight2() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 3, 4, 5, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SMALL_STRAIGHT, dice);

        assertEquals(Integer.valueOf(Globals.SMALL_STRAIGHT_SCORE),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(30, ss.calculateScore());
    }

    public void testScoringSmallStraight3() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(2, 3, 2, 5, 4);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SMALL_STRAIGHT, dice);

        assertEquals(Integer.valueOf(Globals.SMALL_STRAIGHT_SCORE),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(30, ss.calculateScore());
    }

    public void testNotScoringSmallStraight() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 1, 2, 1, 1);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SMALL_STRAIGHT, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());
    }

    public void testNotScoringSmallStraight2() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 2, 3, 1, 3);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.SMALL_STRAIGHT, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.SMALL_STRAIGHT));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringBigStraight() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 2, 3, 4, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.LARGE_STRAIGHT, dice);

        assertEquals(Integer.valueOf(Globals.LARGE_STRAIGHT_SCORE),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(40, ss.calculateScore());
    }

    public void testScoringBigStraight2() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(3, 2, 5, 4, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.LARGE_STRAIGHT, dice);

        assertEquals(Integer.valueOf(Globals.LARGE_STRAIGHT_SCORE),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(40, ss.calculateScore());
    }

    public void testNotScoringBigStraight() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(3, 2, 3, 4, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.LARGE_STRAIGHT, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.LARGE_STRAIGHT));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringYahtzee() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(Globals.YAHTZEE_SCORE),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(50, ss.calculateScore());
    }

    public void testNotScoringYahtzee() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 6, 6, 6, 6);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(0, ss.calculateScore());
    }

    public void testScoringChance() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 6, 6, 2, 3);

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.CHANCE));
        assertEquals(0, ss.calculateScore());

        ss.score(ScoreType.CHANCE, dice);

        assertEquals(Integer.valueOf(18),
                     ss.getScoreSheet().get(ScoreType.CHANCE));
        assertEquals(18, ss.calculateScore());
    }

    public void testScoringYahtzeeBonusAndUpperSection() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);

        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(Globals.YAHTZEE_SCORE),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(50, ss.calculateScore());

        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(30),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(Integer.valueOf(Globals.YAHTZEE_SCORE + Globals.YAHTZEE_BONUS_SCORE),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(180, ss.calculateScore());
    }

    public void testScoringYahtzeeBonusAndOtherCategory() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(6, 6, 6, 6, 6);

        ss.score(ScoreType.SIXES, dice);
        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(30),
                     ss.getScoreSheet().get(ScoreType.SIXES));
        assertEquals(Integer.valueOf(Globals.YAHTZEE_SCORE),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(80, ss.calculateScore());

        ss.score(ScoreType.YAHTZEE, dice);

        assertEquals(Integer.valueOf(Globals.YAHTZEE_SCORE + Globals.YAHTZEE_BONUS_SCORE),
                     ss.getScoreSheet().get(ScoreType.YAHTZEE));
        assertEquals(180, ss.calculateScore());

        assertTrue(ss.canScoreYahtzeeBonus());

        assertEquals(Integer.valueOf(0),
                     ss.getScoreSheet().get(ScoreType.CHANCE));

        ss.score(ScoreType.CHANCE, dice);

        assertEquals(Integer.valueOf(30),
                     ss.getScoreSheet().get(ScoreType.CHANCE));
        assertEquals(210, ss.calculateScore());

        assertFalse(ss.canScoreYahtzeeBonus());
    }

    public void testScoringInSameCategory() {
        ScoreSheet ss = new ScoreSheet();
        Dice[] dice = createDiceArray(1, 1, 1, 1, 1);

        ss.score(ScoreType.ONES, dice);

        assertEquals(Integer.valueOf(5),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(5, ss.calculateScore());

        ss.score(ScoreType.ONES, dice);

        assertEquals(Integer.valueOf(5),
                     ss.getScoreSheet().get(ScoreType.ONES));
        assertEquals(5, ss.calculateScore());
    }
}
