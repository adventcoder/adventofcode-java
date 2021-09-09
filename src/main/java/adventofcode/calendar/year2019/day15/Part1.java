package adventofcode.calendar.year2019.day15;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        IntComputer droid = new IntComputer(input);
        return distanceToOxygenSystem(droid, null);
    }

    public Integer distanceToOxygenSystem(IntComputer droid, Dir skipDir) {
        for (Dir dir : Dir.values()) {
            if (dir == skipDir) continue;
            droid.acceptInput(dir.value());
            int status = droid.nextOutput().intValue();
            if (status == 2) {
                return 1;
            }
            if (status == 1) {
                Integer remainingDistance = distanceToOxygenSystem(droid, dir.opposite());
                if (remainingDistance != null) {
                    return 1 + remainingDistance;
                }
                droid.acceptInput(dir.opposite().value());
                droid.nextOutput();
            }
        }
        return null;
    }
}
