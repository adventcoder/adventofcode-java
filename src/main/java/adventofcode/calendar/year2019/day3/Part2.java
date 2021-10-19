package adventofcode.calendar.year2019.day3;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    public Integer solve(String input) {
        int minDistance = Integer.MAX_VALUE;
        for (Segment segment : Wire.cross(input.split("\n"))) {
            for (Point point : segment) {
                minDistance = Math.min(minDistance, point.distance);
            }
        }
        return minDistance;
    }
}
