package org.yatzy.vendor3.category;

import java.util.List;
import java.util.Map;

public class FullHouse extends Category {

    public FullHouse(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        final Map<Integer, Integer> frequencies = this.frequencies(this.getDice());
        if (frequencies.containsValue(2) && frequencies.containsValue(3)) {
            return this.sum(this.getDice());
        }
        return 0;
    }
}
