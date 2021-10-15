package adventofcode.calendar.year2019.day7;

import adventofcode.calendar.year2019.Intcode;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class Amplifier extends Intcode {
    private static final boolean debug = false;

    private final int index;
    private final BlockingQueue<BigInteger> in;
    private final BlockingQueue<BigInteger> out;

    public Amplifier(String program, int index, BlockingQueue<BigInteger> in, BlockingQueue<BigInteger> out) {
        super(program);
        this.index = index;
        this.in = in;
        this.out = out;
    }

    @Override
    protected BigInteger read() {
        if (debug) {
            System.out.printf("[%d] Inputting ...%n", index);
        }
        try {
            return in.take();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    protected void write(BigInteger value) {
        if (debug) {
            System.out.printf("[%d] Outputting %s ...%n", index, value);
        }
        try {
            out.put(value);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
}
