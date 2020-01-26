package org.yatzy.vendor3.category;

import java.util.List;

public class Yatzy extends Category {

    public Yatzy(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        if (this.frequencies(this.getDice()).containsValue(5)) {
            return 50;
        }
        return 0;
    }
}
