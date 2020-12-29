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
        public Terminal(String program) {
            super(program);
        }

        public Integer run(int systemId) {
            Integer lastOutput = null;
            int testCount = 0;
            reset();
            while (!halted()) {
                step();
                if (waitingForInput()) {
                    resume(BigInteger.valueOf(systemId));
                } else if (waitingForOutput()) {
                    if (lastOutput != null) {
                        testCount++;
                        System.out.println("Test " + testCount + ": " + (lastOutput == 0 ? "PASS" : "FAIL"));
                    }
                    lastOutput = resume().intValue();
                }
            }
            return lastOutput;
        }
    }
}
