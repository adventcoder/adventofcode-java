package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BigInteger maxSignal = BigInteger.ZERO;
        int[] settings = IntStream.range(0, 5).toArray();
        while (IntArray.nextPermutation(settings)) {
            BigInteger signal = run(input, settings);
            if (signal.compareTo(maxSignal) > 0) {
                maxSignal = signal;
            }
        }
        return maxSignal;
    }

    private BigInteger run(String program, int[] settings) {
        BigInteger signal = BigInteger.ZERO;
        for (int setting : settings) {
            BufferedIntcode code = new BufferedIntcode(program);
            code.accept(setting);
            code.accept(signal);
            signal = code.next();
        }
        return signal;
    }
}
