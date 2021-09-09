package adventofcode.calendar.year2019.day19;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int x = 99;
        int y = nextY(input, x, 0);
        while (!test(input, x - 99, y + 99)) {
            x++;
            y = nextY(input, x, y);
        }
        return (x - 99) * 10000 + y;
    }

    private int nextY(String input, int x, int y) {
        while (!test(input, x, y)) {
            y++;
        }
        return y;
    }

    private boolean test(String input, int x, int y) {
        IntComputer droid = new IntComputer(input);
        droid.acceptInput(BigInteger.valueOf(x));
        droid.acceptInput(BigInteger.valueOf(y));
        return droid.nextOutput().intValue() == 1;
    }
}
