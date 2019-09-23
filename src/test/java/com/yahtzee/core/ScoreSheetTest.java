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

        assertEquals((6 * 5) + (5 * 5) + (2 * 4) + 35,
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

        assertEquals((6 * 5) + (5 * 5) + (4 * 5) + 35,
                     ss.calculateScore());
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
