package adventofcode.calendar.year2019.day19;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int count = 0;
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                if (test(input, x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean test(String program, int x, int y) {
        BufferedIntcode droid = new BufferedIntcode(program);
        droid.accept(x);
        droid.accept(y);
        return !droid.next().equals(BigInteger.ZERO);
    }
}
