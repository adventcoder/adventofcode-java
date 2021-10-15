package adventofcode.calendar.year2019.day15;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Droid droid = new Droid(input);
        return droid.moveToOxygenSystem(null);
    }
}
