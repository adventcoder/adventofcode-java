package adventofcode.calendar.year2019.day06;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        OrbitMap map = new OrbitMap(input);
        return map.distanceBetween(map.parent("YOU"), map.parent("SAN"));
    }
}
