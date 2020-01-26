package org.yatzy.vendor3.category;

import java.util.List;

public class NumberOfN extends Category {

    private final int number;

    public NumberOfN(List<Integer> dice, int number) {
        super(dice);
        this.number = number;
    }

    @Override
    public int calculateScore() {
        return Category.frequencies(this.getDice()).get(this.number) * this.number;
    }
}
