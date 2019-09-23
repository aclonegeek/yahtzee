package com.yahtzee.core;

public class Dice {
    private int value;

    Dice() {}
    Dice(int value) {
        this.value = value;
    }

    public void roll() {
        this.value = (int)((Math.random() * 6) + 1);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
