package adventofcode.calendar.year2019.day12;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        // 1. The step function is independent for each axis meaning the cycle time for each axis can be calculated separately.
        // 2. The step function is reversible meaning if there is a cycle, it will return to the initial state.
        MoonSystem orig = new MoonSystem(input);
        MoonSystem copy = new MoonSystem(orig);
        BigInteger totalCycleTime = BigInteger.ONE;
        for (int i = 0; i < Vector.axes.length; i++) {
            int cycleTime = 0;
            do {
                copy.tick(i);
                cycleTime++;
            } while (!copy.equals(orig, i));
            totalCycleTime = lcm(totalCycleTime, BigInteger.valueOf(cycleTime));
        }
        return totalCycleTime;
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);
    }
}
