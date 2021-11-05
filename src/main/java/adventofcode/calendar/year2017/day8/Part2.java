package adventofcode.calendar.year2017.day8;

import adventofcode.framework.AbstractPart;

import java.util.HashMap;
import java.util.Map;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<String, Integer> values = new HashMap<>();
        int maxValue = 0;
        for (String line : input.split("\n")) {
            Instruction instr = new Instruction(line);
            if (instr.test(values)) {
                maxValue = Math.max(maxValue, instr.update(values));
            }
        }
        return maxValue;
    }
}
