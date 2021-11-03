package adventofcode.calendar.year2017.day2;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        for (String line : input.split("\n")) {
            int[] row = IntArray.parse(line, "\\s+");
            int a = IntArray.reduce(Math::min, row);
            int b = IntArray.reduce(Math::max, row);
            sum += b - a;
        }
        return sum;
    }
}
