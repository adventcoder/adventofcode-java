package adventofcode.calendar.year2017.day10;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        KnotHash hash = new KnotHash(IntArray.parse(input, ","));
        hash.round();
        return hash.sparseByte(0) * hash.sparseByte(1);
    }
}
