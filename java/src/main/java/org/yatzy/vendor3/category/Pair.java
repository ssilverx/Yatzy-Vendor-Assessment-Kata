package org.yatzy.vendor3.category;

import java.util.List;

public class Pair extends Category {

    public Pair(List<Integer> dice) {
        super(dice);
    }

    public int calculateScore() {
        return nofakind(2, this.getDice());
    }
}
