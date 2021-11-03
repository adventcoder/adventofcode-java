package adventofcode.calendar.year2017.day3;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int n = Integer.parseInt(input);
        int r = (int) Math.ceil(Math.sqrt(n)) / 2;
        int d = 2 * r;
        int i = (n - (d - 1) * (d - 1)) % d;
        return r + Math.abs(r - i);
    }
}
