package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Maze maze = new Maze(input);
        return maze.getDistance(maze.getLabelPosition("AA"), maze.getLabelPosition("ZZ"));
    }
}
