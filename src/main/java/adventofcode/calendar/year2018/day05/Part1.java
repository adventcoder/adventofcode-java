package adventofcode.calendar.year2018.day05;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        StringBuilder polymer = new StringBuilder(input);
        Polymer.react(polymer);
        return polymer.length();
    }
}
