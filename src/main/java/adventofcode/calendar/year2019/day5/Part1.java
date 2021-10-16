package adventofcode.calendar.year2019.day5;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BigInteger lastOutput = null;
        for (BigInteger output : Intcode.outputs(input, 1)) {
            if (lastOutput != null) {
                System.out.println(lastOutput.equals(BigInteger.ZERO) ? "PASS" : "FAIL");
            }
            lastOutput = output;
        }
        return lastOutput;
    }
}
