package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.Session;
import adventofcode.framework.Solver;
import adventofcode.utils.Enumerable;
import adventofcode.utils.IntArray;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 extends Solver<BigInteger> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 7, new Part2());
    }

    @Override
    public BigInteger solve(String input) {
        return recurse(input, IntStream.range(5, 10).toArray(), 0);
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
        IntComputer[] amplifiers = new IntComputer[setting.length];
        for (int i = 0; i < setting.length; i++) {
            amplifiers[i] = new IntComputer(program);
            amplifiers[i].nextInput(BigInteger.valueOf(setting[i]));
        }
        BigInteger signal = BigInteger.ZERO;
        while (true) {
            BigInteger nextSignal = signal;
            for (IntComputer amplifier : amplifiers) {
                if (amplifier.halting()) {
                    return signal;
                }
                amplifier.nextInput(nextSignal);
                nextSignal = amplifier.nextOutput();
            }
            signal = nextSignal;
        }
    }
}
