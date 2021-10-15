package adventofcode.calendar.year2019.day9;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

import static adventofcode.utils.Iterables.first;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        return first(Intcode.outputs(input, 2));
    }
}
