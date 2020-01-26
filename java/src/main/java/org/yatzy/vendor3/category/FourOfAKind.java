package org.yatzy.vendor3.category;

import java.util.List;

public class FourOfAKind extends Category {

    public FourOfAKind(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        return Category.numberOfAKind(4, this.getDice());
    }
}
