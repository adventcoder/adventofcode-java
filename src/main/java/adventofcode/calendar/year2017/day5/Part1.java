package adventofcode.calendar.year2017.day5;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        // Another brute force...
        int[] offsets = IntArray.parse(input, "\n");
        int i = 0;
        int steps = 0;
        while (i >= 0 && i < offsets.length) {
            i += offsets[i]++;
            steps++;
        }
        return steps;
    }
}
