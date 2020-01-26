package org.yatzy.vendor3.category;

import java.util.List;

public class NumberOfAKind extends Category {

    private final int number;

    public NumberOfAKind(List<Integer> dice, int number) {
        super(dice);
        this.number = number;
    }

    @Override
    public int calculateScore() {
        return this.numberOfAKind(this.number, this.getDice());
    }
}
