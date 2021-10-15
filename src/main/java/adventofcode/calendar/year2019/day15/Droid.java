package adventofcode.calendar.year2019.day15;

import adventofcode.calendar.year2019.BufferedIntcode;

public class Droid {
    private final BufferedIntcode droid;
    private int status = 1;

    public Droid(String program) {
        this.droid = new BufferedIntcode(program);
    }

    public boolean atOxygenSystem() {
        return status == 2;
    }

    public boolean tryMove(Dir dir) {
        droid.accept(dir.intValue());
        status = droid.next().intValueExact();
        return status != 0;
    }

    public void move(Dir dir) {
        if (!tryMove(dir)) {
            throw new IllegalStateException();
        }
    }

    public Integer moveToOxygenSystem(Dir skipDir) {
        if (atOxygenSystem()) {
            return 0;
        }
        for (Dir dir : Dir.values()) {
            if (dir == skipDir) continue;
            if (tryMove(dir)) {
                Integer remainingDistance = moveToOxygenSystem(dir.opposite());
                if (remainingDistance != null) {
                    return 1 + remainingDistance;
                }
                move(dir.opposite());
            }
        }
        return null;
    }

    public int maxDistanceFromHere(Dir skipDir) {
        int maxDistance = 0;
        for (Dir dir : Dir.values()) {
            if (dir == skipDir) continue;
            if (tryMove(dir)) {
                maxDistance = Math.max(maxDistance, 1 + maxDistanceFromHere(dir.opposite()));
                move(dir.opposite());
            }
        }
        return maxDistance;
    }
}
