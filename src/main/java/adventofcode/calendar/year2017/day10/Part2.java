package adventofcode.calendar.year2017.day10;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        return KnotHash.standard(input.trim()).toHexString();
    }
}
