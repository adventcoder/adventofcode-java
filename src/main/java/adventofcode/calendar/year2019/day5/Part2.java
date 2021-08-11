package adventofcode.calendar.year2019.day5;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        IntComputer computer = new IntComputer(input);
        computer.acceptInput(BigInteger.valueOf(5));
        return computer.nextOutput();
    }
}
