package adventofcode.calendar.year2019.day01;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int totalFuel = 0;
        for (String line : input.split("\n")) {
            int mass = Integer.parseInt(line);
            totalFuel += mass / 3 - 2;
        }
        return totalFuel;
    }
}
