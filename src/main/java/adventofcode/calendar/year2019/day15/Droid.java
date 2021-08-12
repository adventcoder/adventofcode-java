package adventofcode.calendar.year2019.day15;

import adventofcode.calendar.year2019.common.IntComputer;

import java.math.BigInteger;

public class Droid extends IntComputer {
    public Droid(String program) {
        super(program);
    }

    public Integer moveToOxygenSystem(Integer prevDir) {
        for (int dir = 1; dir <= 4; dir++) {
            if (prevDir != null && dir == opposite(prevDir)) {
                continue;
            }
            int status = move(dir);
            if (status == 1) {
                Integer remainingDistance = moveToOxygenSystem(dir);
                if (remainingDistance != null) {
                    return remainingDistance + 1;
                }
                move(opposite(dir));
            } else if (status == 2) {
                return 1;
            }
        }
        return null;
    }

    public int findMaxDistance(Integer prevDir) {
        int maxDistance = 0;
        for (int dir = 1; dir <= 4; dir++) {
            if (prevDir != null && dir == opposite(prevDir)) {
                continue;
            }
            int status = move(dir);
            if (status == 1 || status == 2) {
                maxDistance = Math.max(maxDistance, findMaxDistance(dir) + 1);
                move(opposite(dir));
            }
        }
        return maxDistance;
    }

    public int move(int dir) {
        acceptInput(BigInteger.valueOf(dir));
        return nextOutput().intValue();
    }

    public int opposite(int dir) {
        return ((dir - 1) ^ 1) + 1;
    }
}
