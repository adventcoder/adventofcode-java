package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        RecursiveMaze maze = new RecursiveMaze(input);
        return maze.findDistance(maze.getLabelPosition("AA"), maze.getLabelPosition("ZZ"));
    }
}
