package org.yatzy.vendor3.category;

import java.util.List;

public class ThreeOfAKind extends Category {

    public ThreeOfAKind(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        return Category.numberOfAKind(3, this.getDice());
    }
}
