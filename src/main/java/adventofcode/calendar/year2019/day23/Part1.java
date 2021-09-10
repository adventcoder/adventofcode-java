package adventofcode.calendar.year2019.day23;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        return new Network(input) {
            @Override
            public void send(int from, int to, BigInteger x, BigInteger y) {
                if (to == 255) {
                    throw new Answer(y);
                }
                super.send(from, to, x, y);
            }
        }.answer();
    }
}
