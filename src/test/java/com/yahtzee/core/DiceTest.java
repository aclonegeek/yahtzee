package com.yahtzee.core;

import junit.framework.TestCase;

public class DiceTest extends TestCase {
    public void testRoll() {
        Dice dice = new Dice();

        for (int i = 0; i < 10; i++) {
            dice.roll();
            assertTrue(dice.getValue() > 0 && dice.getValue() < 7);
        }
    }
}
