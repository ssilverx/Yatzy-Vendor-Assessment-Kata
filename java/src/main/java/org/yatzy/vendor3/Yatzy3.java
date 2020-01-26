package org.yatzy.vendor3;

import org.yatzy.RollInput;
import org.yatzy.YatzyCalculator;
import org.yatzy.vendor3.category.Category;
import org.yatzy.vendor3.category.Chance;
import org.yatzy.vendor3.category.FullHouse;
import org.yatzy.vendor3.category.InvalidCategory;
import org.yatzy.vendor3.category.LargeStraight;
import org.yatzy.vendor3.category.NumberOfAKind;
import org.yatzy.vendor3.category.NumberOfN;
import org.yatzy.vendor3.category.Pair;
import org.yatzy.vendor3.category.SmallStraight;
import org.yatzy.vendor3.category.TwoPairs;
import org.yatzy.vendor3.category.Yatzy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Yatzy3 implements YatzyCalculator {

    private static final Map<String, Function<List<Integer>, ? extends Category>> CATEGORIES = new LinkedHashMap<>();

    static {
        CATEGORIES.put("chance", (dice) -> new Chance(dice));
        CATEGORIES.put("yatzy", (dice) -> new Yatzy(dice));
        CATEGORIES.put("ones", (dice) -> new NumberOfN(dice, 1));
        CATEGORIES.put("twos", (dice) -> new NumberOfN(dice, 2));
        CATEGORIES.put("threes", (dice) -> new NumberOfN(dice, 3));
        CATEGORIES.put("fours", (dice) -> new NumberOfN(dice, 4));
        CATEGORIES.put("fives", (dice) -> new NumberOfN(dice, 5));
        CATEGORIES.put("sixes", (dice) -> new NumberOfN(dice, 6));
        CATEGORIES.put("pair", (dice) -> new Pair(dice));
        CATEGORIES.put("twopairs", (dice) -> new TwoPairs(dice));
        CATEGORIES.put("threeofakind", (dice) -> new NumberOfAKind(dice, 3));
        CATEGORIES.put("fourofakind", (dice) -> new NumberOfAKind(dice, 4));
        CATEGORIES.put("smallstraight", (dice) -> new SmallStraight(dice));
        CATEGORIES.put("largestraight", (dice) -> new LargeStraight(dice));
        CATEGORIES.put("fullhouse", (dice) -> new FullHouse(dice));
    }

    @Override
    public List<String> validCategories() {
        return new ArrayList<>(CATEGORIES.keySet());
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
        return CATEGORIES.getOrDefault(category, (ignored) -> new InvalidCategory(ignored))
                         .apply(dice)
                         .calculateScore();
    }
}
