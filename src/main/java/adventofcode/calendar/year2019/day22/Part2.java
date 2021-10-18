package adventofcode.calendar.year2019.day22;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Shuffle shuffle = new Shuffle(BigInteger.valueOf(119315717514047L));
        shuffle.perform(input);
        shuffle.repeat(BigInteger.valueOf(101741582076661L));
        return shuffle.applyInverse(BigInteger.valueOf(2020));
    }
}
