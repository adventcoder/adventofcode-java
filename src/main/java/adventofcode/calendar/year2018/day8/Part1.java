package adventofcode.calendar.year2018.day8;

import adventofcode.framework.AbstractPart;

import java.util.StringTokenizer;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return new Tree(new StringTokenizer(input)).checksum();
    }
}
