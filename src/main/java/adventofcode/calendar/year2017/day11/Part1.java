package adventofcode.calendar.year2017.day11;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        HexPoint point = new HexPoint();
        for (String dir : input.split(",")) {
            point.add(dir);
        }
        return point.abs();
    }
}
