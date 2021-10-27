package adventofcode.calendar.year2018.day5;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int minLength = Integer.MAX_VALUE;
        for (char unit = 'a'; unit <= 'z'; unit++) {
            StringBuilder polymer = new StringBuilder(input);
            Polymer.delete(polymer, unit);
            Polymer.react(polymer);
            minLength = Math.min(minLength, polymer.length());
        }
        return minLength;
    }
}
