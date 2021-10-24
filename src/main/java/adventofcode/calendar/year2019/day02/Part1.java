package adventofcode.calendar.year2019.day02;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Intcode code = new Intcode(input);
        code.set(1, 12);
        code.set(2, 2);
        code.run();
        return code.get(0); 
    }
}
