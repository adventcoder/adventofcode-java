package adventofcode.calendar.year2019.day06;

import adventofcode.framework.AbstractPart;

import static adventofcode.utils.Iterables.sum;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        OrbitMap map = new OrbitMap(input);
        return sum(map::getHeight, map.masses());
    }
}
