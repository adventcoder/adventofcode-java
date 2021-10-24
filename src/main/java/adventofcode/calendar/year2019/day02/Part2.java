package adventofcode.calendar.year2019.day02;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (run(input, noun, verb) == 19690720) {
                    return noun * 100 + verb;
                }
            }
        }
        return null;
    }

    private int run(String program, int noun, int verb) {
        Intcode code = new Intcode(program);
        code.set(1, noun);
        code.set(2, verb);
        code.run();
        return code.getAsInt(0);
    }
}
