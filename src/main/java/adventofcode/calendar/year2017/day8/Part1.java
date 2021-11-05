package adventofcode.calendar.year2017.day8;

import adventofcode.framework.AbstractPart;

import java.util.*;

import static adventofcode.utils.Iterables.max;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<String, Integer> values = new HashMap<>();
        for (String line : input.split("\n")) {
            Instruction instr = new Instruction(line);
            instr.apply(values);
        }
        return max(Comparator.naturalOrder(), values.values());
    }
}
