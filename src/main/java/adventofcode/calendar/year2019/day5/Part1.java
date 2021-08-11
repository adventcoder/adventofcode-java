package adventofcode.calendar.year2019.day5;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        IntComputer computer = new IntComputer(input);
        computer.acceptInput(BigInteger.ONE);
        BigInteger lastOutput = null;
        while (computer.hasNextOutput()) {
            if (lastOutput != null) {
                System.out.println(lastOutput.equals(BigInteger.ZERO) ? "PASS" : "FAIL");
            }
            lastOutput = computer.nextOutput();
        }
        return lastOutput;
    }
}
