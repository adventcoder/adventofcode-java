package adventofcode.calendar.year2017.day10;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

public class Part2 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        KnotHash hash = new KnotHash(input.trim());
        for (int i = 0; i < 64; i++) {
            hash.round();
        }
        return hash.toString();
    }
}
