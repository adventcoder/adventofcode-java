package adventofcode.calendar.year2019.day22;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Shuffle shuffle = new Shuffle(BigInteger.valueOf(10007));
        shuffle.perform(input);
        return shuffle.apply(BigInteger.valueOf(2019));
    }
}
