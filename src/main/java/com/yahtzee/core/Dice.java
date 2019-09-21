package com.yahtzee.core;

public class Dice {
    private int value;

    public void roll() {
        this.value = (int)((Math.random() * 6) + 1);
    }

    public int getValue() {
        return this.value;
    }
}
