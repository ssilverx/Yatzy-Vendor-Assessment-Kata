package org.yatzy.vendor3.category;

import java.util.List;

public class SmallStraight extends Category{

    public SmallStraight(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        if (this.isStraight(this.getDice()) && this.frequencies(this.getDice()).get(6) == 0) {
            return this.sum(this.getDice());
        }
        return 0;
    }
}
