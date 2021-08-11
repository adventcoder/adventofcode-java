package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        return recurse(input, IntStream.range(0, 5).toArray(), 0);
    }

    private BigInteger recurse(String program, int[] setting, int size) {
        if (size == setting.length) {
            return run(program, setting);
        } else {
            BigInteger maxSignal = recurse(program, setting, size + 1);
            for (int i = size + 1; i < setting.length; i++) {
                IntArray.swap(setting, size, i);
                BigInteger signal = recurse(program, setting, size + 1);
                if (signal.compareTo(maxSignal) > 0) {
                    maxSignal = signal;
                }
                IntArray.swap(setting, size, i);
            }
            return maxSignal;
        }
    }

    private BigInteger run(String program, int[] setting) {
        BigInteger signal = BigInteger.ZERO;
        for (int i = 0; i < setting.length; i++) {
            IntComputer amplifier = new IntComputer(program);
            amplifier.acceptInput(BigInteger.valueOf(setting[i]));
            amplifier.acceptInput(signal);
            signal = amplifier.nextOutput();
        }
        return signal;
    }
}
