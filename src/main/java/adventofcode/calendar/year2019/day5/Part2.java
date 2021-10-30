package adventofcode.calendar.year2019.day5;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.Iterables;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        return Iterables.first(Intcode.outputs(input, 5));
    }
}
