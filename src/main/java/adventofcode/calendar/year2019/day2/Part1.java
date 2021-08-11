package adventofcode.calendar.year2019.day2;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        IntComputer comp = new IntComputer(input);
        comp.set(1, BigInteger.valueOf(12));
        comp.set(2, BigInteger.valueOf(2));
        comp.run();
        return comp.get(0); 
    }
}
