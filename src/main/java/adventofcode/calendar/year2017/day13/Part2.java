package adventofcode.calendar.year2017.day13;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        DiscreteSet startTimes = new DiscreteSet(0, 1);
        for (String line : input.split("\n")) {
            String[] pair = line.split(":\\s*");
            int depth = Integer.parseInt(pair[0]);
            int range = Integer.parseInt(pair[1]);
            startTimes = startTimes.delete(-depth, 2 * (range - 1));
        }
        return startTimes.get(0);
    }
}
