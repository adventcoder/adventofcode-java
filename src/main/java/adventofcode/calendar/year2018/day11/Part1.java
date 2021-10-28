package adventofcode.calendar.year2018.day11;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<String> {
    private static final int size = 300;

    @Override
    public String solve(String input) {
        Grid grid = new Grid(Integer.parseInt(input));
        String maxCoord = null;
        int maxPower = Integer.MIN_VALUE;
        for (int y = 1; y <= size - 2; y++) {
            for (int x = 1; x <= size - 2; x++) {
                int power = grid.power(x, y, 3);
                if (power >= maxPower) {
                    maxPower = power;
                    maxCoord = x + "," + y;
                }
            }
        }
        return maxCoord;
    }
}
