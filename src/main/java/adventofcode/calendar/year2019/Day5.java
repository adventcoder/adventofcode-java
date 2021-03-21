package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;

import java.math.BigInteger;
import java.util.function.Consumer;

public class Day5 extends AbstractDay {
    public Day5() {
        super(2019, 5);
    }

    @Override
    public void solve(String input, Consumer<Object> answers) {
        Terminal terminal = new Terminal(input);
        answers.accept(terminal.run(1));
        answers.accept(terminal.run(5));
    }

    public static class Terminal extends IntComputer {
        private Integer systemId;
        private Integer lastOutput;
        private int testCount;

        public Terminal(String program) {
            super(program);
        }

        @Override
        protected BigInteger input() {
            return BigInteger.valueOf(systemId);
        }

        @Override
        protected boolean output(BigInteger value) {
            if (lastOutput != null) {
                testCount++;
                System.out.println("Test " + testCount + ": " + (lastOutput == 0 ? "PASS" : "FAIL"));
            }
            lastOutput = value.intValue();
            return true;
        }

        public Integer run(int systemId) {
            reset();
            this.systemId = systemId;
            lastOutput = null;
            testCount = 0;
            while (!halted()) {
                step();
            }
            return lastOutput;
        }
    }
}
