package adventofcode.calendar.year2018.day22;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Cave cave = Cave.parse(input);
        return cave.riskLevel();
    }
}
