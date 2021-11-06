package adventofcode.calendar.year2017.day14;

import adventofcode.calendar.year2017.day10.KnotHash;
import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int count = 0;
        for (int y = 0; y < 128; y++) {
            count += KnotHash.standard(input + "-" + y).toBigInteger().bitCount();
        }
        return count;
    }
}
