package adventofcode.calendar.year2019.day23;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;
import java.util.*;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        return new Network(input) {
            private Set<BigInteger> seen = new HashSet<>();

            @Override
            public void send(int from, int to, BigInteger x, BigInteger y) {
                if (from == 255 && to == 0 && !seen.add(y)) {
                    throw new Answer(y);
                }
                super.send(from, to, x, y);
            }
        }.answer();
    }
}
