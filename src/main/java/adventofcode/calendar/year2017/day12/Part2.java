package adventofcode.calendar.year2017.day12;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return new Graph(input).findGroupSizes().size();
    }
}
