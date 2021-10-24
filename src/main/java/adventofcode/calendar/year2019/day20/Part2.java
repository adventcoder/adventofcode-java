package adventofcode.calendar.year2019.day20;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return new HyperMaze(input).getDistance(new HyperPortal("AA", false, 0), new HyperPortal("ZZ", false, 0));
    }
}
