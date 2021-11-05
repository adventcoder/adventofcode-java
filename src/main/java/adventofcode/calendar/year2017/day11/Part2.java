package adventofcode.calendar.year2017.day11;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        HexPoint point = new HexPoint();
        int maxDistance = 0;
        for (String dir : input.split(",")) {
            point.add(dir);
            maxDistance = Math.max(maxDistance, point.abs());
        }
        return maxDistance;
    }
}
