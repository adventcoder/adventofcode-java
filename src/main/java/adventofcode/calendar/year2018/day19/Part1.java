package adventofcode.calendar.year2018.day19;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Process process = new Process(input);
        while (process.running()) {
            process.step();
        }
        return process.reg[0];
    }
}
