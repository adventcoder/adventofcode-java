package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return new Maze(input).getDistance(new Portal("AA", false), new Portal("ZZ", false));
    }
}
