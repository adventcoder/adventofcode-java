package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Maze maze = new Maze(input);
        return maze.getHyperDistance(new Portal("AA", false), new Portal("ZZ", false));
    }
}
