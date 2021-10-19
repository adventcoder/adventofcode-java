package adventofcode.calendar.year2019.day3;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    public Integer solve(String input) {
        int minDistance = Integer.MAX_VALUE;
        for (Segment segment : Wire.cross(input.split("\n"))) {
            for (Point point : segment) {
                minDistance = Math.min(minDistance, point.distanceFromOrigin());
            }
        }
        return minDistance;
    }
}
