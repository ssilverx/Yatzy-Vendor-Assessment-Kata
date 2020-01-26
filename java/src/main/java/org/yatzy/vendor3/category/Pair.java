package org.yatzy.vendor3.category;

import java.util.List;

public class Pair extends Category {

    public Pair(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        return this.numberOfAKind(2, this.getDice());
    }
}
