package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;

import java.math.BigInteger;
import java.util.function.Consumer;

public class Day7 extends AbstractDay {
    public Day7() {
        super(2019, 7);
    }

    @Override
    public void solve(String input, Consumer<Object> answers) {
        int[] phases = { 1, 2, 3, 4, 5 };
        Amplifier[] amplifiers = new Amplifier[phases.length];
        for (int i = phases.length - 1; i >= 0; i--) {
            Amplifier next = i + 1 < phases.length ? amplifiers[i + 1] : null;
            amplifiers[i] = new Amplifier(input, phases[i], next);
        }
        amplifiers[phases.length - 1].next = amplifiers[0];
        amplifiers[0].signal = BigInteger.valueOf(0);
        while (true) {
            boolean progress = false;
            for (Amplifier amplifier : amplifiers) {
                if (amplifier.step()) {
                    progress = true;
                }
            }
            if (!progress) break;
        }
        answers.accept(amplifiers[0].signal);
    }

    public static class Amplifier extends IntComputer {
        public int phase;
        public Amplifier next;
        boolean initialized;
        public BigInteger signal;

        public Amplifier(String program, int phase, Amplifier next) {
            super(program);
            this.phase = phase;
            this.next = next;
        }

        @Override
        public void reset() {
            super.reset();
            initialized = false;
            signal = null;
        }

        @Override
        protected BigInteger input() {
            if (!initialized) {
                initialized = true;
                return BigInteger.valueOf(phase);
            } else {
                if (signal == null) return null;
                BigInteger input = signal;
                signal = null;
                return input;
            }
        }

        @Override
        protected boolean output(BigInteger value) {
            if (next.signal != null) return false;
            next.signal = value;
            return true;
        }
    }
}
