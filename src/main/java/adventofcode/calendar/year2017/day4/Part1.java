package adventofcode.calendar.year2017.day4;

import adventofcode.framework.AbstractPart;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static adventofcode.utils.Iterables.tally;

public class Part1 extends AbstractPart<Integer> {
    public Integer solve(String input) {
        return tally(this::isValid, Arrays.asList(input.split("\n")));
    }

    private boolean isValid(String phrase) {
        Set<String> set = new HashSet<>();
        for (String word : phrase.split("\\s+")) {
            if (!set.add(word)) return false;
        }
        return true;
    }
}
