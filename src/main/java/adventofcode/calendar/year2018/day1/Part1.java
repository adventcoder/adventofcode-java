package adventofcode.calendar.year2018.day1;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int freq = 0;
        for (String line : input.split("\n")) {
            freq += Integer.parseInt(line);
        }
        return freq;
    }
}
