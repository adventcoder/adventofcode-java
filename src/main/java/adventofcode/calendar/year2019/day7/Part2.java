package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BigInteger maxSignal = BigInteger.ZERO;
        int[] settings = IntStream.range(5, 10).toArray();
        while (IntArray.nextPermutation(settings)) {
            BigInteger signal = run(input, settings);
            if (signal.compareTo(maxSignal) > 0) {
                maxSignal = signal;
            }
        }
        return maxSignal;
    }

    private BigInteger run(String program, int[] settings) {
        IntComputer[] amplifiers = new IntComputer[settings.length];
        for (int i = 0; i < settings.length; i++) {
            amplifiers[i] = new IntComputer(program);
            amplifiers[i].acceptInput(BigInteger.valueOf(settings[i]));
        }
        BigInteger signal = BigInteger.ZERO;
        while (true) {
            BigInteger nextSignal = signal;
            for (IntComputer amplifier : amplifiers) {
                if (amplifier.halting()) {
                    return signal;
                }
                amplifier.acceptInput(nextSignal);
                nextSignal = amplifier.nextOutput();
            }
            signal = nextSignal;
        }
    }
}
