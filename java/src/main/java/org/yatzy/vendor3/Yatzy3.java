package org.yatzy.vendor3;

import org.yatzy.RollInput;
import org.yatzy.YatzyCalculator;
import org.yatzy.vendor3.category.Category;
import org.yatzy.vendor3.category.Chance;
import org.yatzy.vendor3.category.Pair;
import org.yatzy.vendor3.category.TwoPairs;
import org.yatzy.vendor3.category.Yatzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Yatzy3 implements YatzyCalculator {

    private final Map<String, Function<List<Integer>, ? extends Category>> categories = Map.of(
            "chance", (dice) -> new Chance(dice),
            "yatzy", (dice) -> new Yatzy(dice),
            "pair", (dice) -> new Pair(dice),
            "twopairs", (dice) -> new TwoPairs(dice));

    @Override
    public List<String> validCategories() {
        return Arrays.asList("chance", "yatzy", "ones", "twos", "threes", "fours", "fives", "sixes",
                "pair", "twopairs", "threeofakind", "fourofakind",
                "smallstraight", "largestraight", "fullhouse");
    }

    @Override
    public RollInput parseDiceAndCategory(String inputLine) {
        final String[] fields = inputLine.split(" ");
        final String[] numbers = fields[0].split(",");
        final ArrayList<Integer> dice = new ArrayList<Integer>();
        for (String number : numbers) {
            dice.add(Integer.parseInt(number));
        }
        return new RollInput(inputLine, dice, fields[1]);
    }

    @Override
    public int score(List<Integer> dice, String category) {
        final Function<List<Integer>, ? extends Category> function = this.categories.get(category);
        if (function != null) {
            return function.apply(dice).calculateScore();
        }

        switch (category) {
            case "ones":
                return this.ones(dice);
            case "twos":
                return this.twos(dice);
            case "threes":
                return this.threes(dice);
            case "fours":
                return this.fours(dice);
            case "fives":
                return this.fives(dice);
            case "sixes":
                return this.sixes(dice);
            case "threeofakind":
                return this.threeofakind(dice);
            case "fourofakind":
                return this.fourofakind(dice);
            case "smallstraight":
                return this.smallstraight(dice);
            case "largestraight":
                return this.largestraight(dice);
            case "fullhouse":
                return this.fullhouse(dice);
        }
        return -1;
    }

    int numberFrequency(int number, List<Integer> dice) {
        return Category.frequencies(dice).get(number)*number;
    }

    public int ones(List<Integer> dice) {
        return this.numberFrequency(1, dice);
    }
    public int twos(List<Integer> dice) {
        return this.numberFrequency(2, dice);
    }
    public int threes(List<Integer> dice) {
        return this.numberFrequency(3, dice);
    }
    public int fours(List<Integer> dice) {
        return this.numberFrequency(4, dice);
    }
    public int fives(List<Integer> dice) {
        return this.numberFrequency(5, dice);
    }
    public int sixes(List<Integer> dice) {
        return this.numberFrequency(6, dice);
    }

    public int threeofakind(List<Integer> dice) {
        return Category.nofakind(3, dice);
    }

    public int fourofakind(List<Integer> dice) {
        return Category.nofakind(4, dice);
    }

    public int smallstraight(List<Integer> dice) {
        if (Category.isStraight(dice) && Category.frequencies(dice).get(6) == 0) {
            return Category.sum(dice);
        }
        return 0;
    }

    public int largestraight(List<Integer> dice) {
        if (Category.isStraight(dice) && Category.frequencies(dice).get(1) == 0) {
            return Category.sum(dice);
        }
        return 0;
    }

    public int fullhouse(List<Integer> dice) {
        final Map<Integer, Integer> frequencies = Category.frequencies(dice);
        if (frequencies.containsValue(2) && frequencies.containsValue(3)) {
            return Category.sum(dice);
        }
        return 0;
    }
}
