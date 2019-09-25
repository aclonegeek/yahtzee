package com.yahtzee.core;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    public void testNoReRolls() {
        Player p = new Player("p");
        assertEquals(0, p.getNumberOfRolls());
        p.roll();
        assertEquals(1, p.getNumberOfRolls());
    }

    public void testOneReRoll() {
        Player p = new Player("p");
        assertEquals(0, p.getNumberOfRolls());
        p.roll();
        assertEquals(1, p.getNumberOfRolls());

        Dice[] pDice = p.getDice();
        int firstDiceValue = pDice[0].getValue();
        int secondDiceValue = pDice[1].getValue();
        int thirdDiceValue = pDice[2].getValue();
        int fourthDiceValue = pDice[3].getValue();

        p.reroll("1 2 3 4");
        assertEquals(2, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());
        assertEquals(secondDiceValue, pDice[1].getValue());
        assertEquals(thirdDiceValue, pDice[2].getValue());
        assertEquals(fourthDiceValue, pDice[3].getValue());
    }

    public void testTwoReRolls() {
        Player p = new Player("p");
        assertEquals(0, p.getNumberOfRolls());
        p.roll();
        assertEquals(1, p.getNumberOfRolls());

        Dice[] pDice = p.getDice();
        int firstDiceValue = pDice[0].getValue();

        p.reroll("1");
        assertEquals(2, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());

        int secondDiceValue = pDice[1].getValue();
        int fourthDiceValue = pDice[3].getValue();
        int fifthDiceValue = pDice[4].getValue();

        p.reroll("1 2 4 5");
        assertEquals(3, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());
        assertEquals(secondDiceValue, pDice[1].getValue());
        assertEquals(fourthDiceValue, pDice[3].getValue());
        assertEquals(fifthDiceValue, pDice[4].getValue());
    }

    public void testAttemptingThreeReRolls() {
        Player p = new Player("p");
        assertEquals(0, p.getNumberOfRolls());
        p.roll();
        assertEquals(1, p.getNumberOfRolls());

        Dice[] pDice = p.getDice();
        int fifthDiceValue = pDice[4].getValue();

        p.reroll("5");
        assertEquals(2, p.getNumberOfRolls());
        assertEquals(fifthDiceValue, pDice[4].getValue());

        int firstDiceValue = pDice[0].getValue();
        int thirdDiceValue = pDice[2].getValue();

        p.reroll("1 3 5");
        assertEquals(3, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());
        assertEquals(thirdDiceValue, pDice[2].getValue());
        assertEquals(fifthDiceValue, pDice[4].getValue());

        int secondDiceValue = pDice[1].getValue();
        int fourthDiceValue = pDice[3].getValue();

        p.reroll("2 4");
        assertEquals(3, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());
        assertEquals(secondDiceValue, pDice[1].getValue());
        assertEquals(thirdDiceValue, pDice[2].getValue());
        assertEquals(fourthDiceValue, pDice[3].getValue());
        assertEquals(fifthDiceValue, pDice[4].getValue());

        p.reroll("");
        assertEquals(3, p.getNumberOfRolls());
        assertEquals(firstDiceValue, pDice[0].getValue());
        assertEquals(secondDiceValue, pDice[1].getValue());
        assertEquals(thirdDiceValue, pDice[2].getValue());
        assertEquals(fourthDiceValue, pDice[3].getValue());
        assertEquals(fifthDiceValue, pDice[4].getValue());
    }

    public void testReRollAllDice() {
        Player p = new Player("p");
        assertEquals(0, p.getNumberOfRolls());
        p.roll();
        assertEquals(1, p.getNumberOfRolls());
        p.reroll("");
        assertEquals(2, p.getNumberOfRolls());
    }
}
