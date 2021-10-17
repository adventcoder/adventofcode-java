package adventofcode.calendar.year2019.day15;

import adventofcode.framework.AbstractPart;

//TODO: revisit
public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Droid droid = new Droid(input);
        if (droid.moveToOxygenSystem(null) != null) {
            return droid.maxDistanceFromHere(null);
        }
        return null;
    }
}
