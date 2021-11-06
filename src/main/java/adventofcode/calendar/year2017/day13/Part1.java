package adventofcode.calendar.year2017.day13;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int totalSeverity = 0;
        for (String line : input.split("\n")) {
            String[] pair = line.split(":\\s*", 2);
            int depth = Integer.parseInt(pair[0]);
            int range = Integer.parseInt(pair[1]);
            if (depth % (2 * (range - 1)) == 0) {
                totalSeverity += depth * range;
            }
        }
        return totalSeverity;
    }
}
