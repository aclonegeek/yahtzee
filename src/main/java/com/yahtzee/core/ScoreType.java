package com.yahtzee.core;

public enum ScoreType {
    ONES("Ones"),
    TWOS("Twos"),
    THREES("Threes"),
    FOURS("Fours"),
    FIVES("Fives"),
    SIXES("Sixes"),
    BONUS("Bonus"),
    LARGE_STRAIGHT("Large straight"),
    SMALL_STRAIGHT("Small straight"),
    FULL_HOUSE("Full House"),
    THREE_OF_A_KIND("Three of a Kind"),
    FOUR_OF_A_KIND("Four of a Kind"),
    CHANCE("Chance"),
    YAHTZEE("Yahtzee");

    private final String name;

    private ScoreType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
