package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.Session;
import adventofcode.framework.Solver;
import adventofcode.utils.IntArray;

import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class Part1 extends Solver<BigInteger> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 7, new Part1());
    }

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
            amplifier.nextInput(BigInteger.valueOf(setting[i]));
            amplifier.nextInput(signal);
            signal = amplifier.nextOutput();
        }
        return signal;
    }
}
