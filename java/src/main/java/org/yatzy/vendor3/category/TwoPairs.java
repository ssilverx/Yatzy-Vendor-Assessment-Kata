package org.yatzy.vendor3.category;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairs extends Category{

    public TwoPairs(List<Integer> dice) {
        super(dice);
    }

    @Override
    public int calculateScore() {
        final Map<Integer, Integer> frequencies = Category.frequencies(this.getDice());
        int score = 0;
        if (Category.frequencies(this.getDice()).values().stream().filter(f -> f == 2).collect(Collectors.toList()).size() == 2) {
            for (int i : Arrays.asList(6, 5, 4, 3, 2, 1)) {
                if (frequencies.get(i) >= 2) {
                    score += i * 2;
                }
            }
        }
        return score;
    }
}
