package org.yatzy.vendor3.category;

import java.util.List;

public class Ones extends Category {

    private final int number;

    public Ones(List<Integer> dice, int number) {
        super(dice);
        this.number = number;
    }

    @Override
    public int calculateScore() {
        return numberFrequency(this.number, this.getDice());
    }

    public static int numberFrequency(int number, List<Integer> dice) {
        return Category.frequencies(dice).get(number) * number;
    }
}
