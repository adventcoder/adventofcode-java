package adventofcode.calendar.year2018.day10;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        PointList points = new PointList(input);
        return points.minimizeVariance();
    }
}
