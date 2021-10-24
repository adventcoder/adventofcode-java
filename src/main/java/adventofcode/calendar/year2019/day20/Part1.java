package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Maze grid = new Maze(input);
        return grid.getDistance(grid.getPortalPosition(new Portal("AA", false)), grid.getPortalPosition(new Portal("ZZ", false)));
    }
}
