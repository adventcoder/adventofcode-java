package adventofcode.calendar.year2018.day22;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Cave cave = Cave.parse(input);
        // This is kinda slow...
        return Rescue.start().findTimeToGoal(cave);
    }
}