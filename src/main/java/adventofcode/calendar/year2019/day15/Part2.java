package adventofcode.calendar.year2019.day15;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        IntComputer droid = new IntComputer(input);
        if (moveToOxygenSystem(droid, null)) {
            return maxDistance(droid, null);
        }
        return null;
    }

    public boolean moveToOxygenSystem(IntComputer droid, Dir skipDir) {
        for (Dir dir : Dir.values()) {
            if (dir == skipDir) continue;
            droid.acceptInput(dir.value());
            int status = droid.nextOutput().intValue();
            if (status == 0) continue;
            if (status == 2 || (status == 1 && moveToOxygenSystem(droid, dir.opposite()))) {
                return true;
            }
            droid.acceptInput(dir.opposite().value());
            droid.nextOutput();
        }
        return false;
    }

    private int maxDistance(IntComputer droid, Dir skipDir) {
        int maxDistance = 0;
        for (Dir dir : Dir.values()) {
            if (dir == skipDir) continue;
            droid.acceptInput(dir.value());
            int status = droid.nextOutput().intValue();
            if (status == 0) continue;
            maxDistance = Math.max(maxDistance, 1 + maxDistance(droid, dir.opposite()));
            droid.acceptInput(dir.opposite().value());
            droid.nextOutput();
        }
        return maxDistance;
    }
}
