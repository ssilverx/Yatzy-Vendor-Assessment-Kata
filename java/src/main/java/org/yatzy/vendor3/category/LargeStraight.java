package org.yatzy.vendor3.category;

import java.util.List;

public class LargeStraight extends Category {

    public LargeStraight(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        if (Category.isStraight(this.getDice()) && Category.frequencies(this.getDice()).get(1) == 0) {
            return Category.sum(this.getDice());
        }
        return 0;
    }
}
