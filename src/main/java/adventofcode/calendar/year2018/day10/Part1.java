package adventofcode.calendar.year2018.day10;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        PointList points = new PointList(input);
        points.tick(points.minimizeVariance());
        return points.toString();
    }
}
