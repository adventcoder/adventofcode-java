package adventofcode.calendar.year2018.day11;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<String> {
    public static final int size = 300;

    @Override
    public String solve(String input) {
        CachedGrid grid = new CachedGrid(Integer.parseInt(input), size);
        String maxCoord = null;
        int maxPower = Integer.MIN_VALUE;
        for (int squareSize = 1; squareSize <= size; squareSize++) {
            for (int y = 1; y <= size - squareSize + 1; y++) {
                for (int x = 1; x <= size - squareSize + 1; x++) {
                    int power = grid.power(x, y, squareSize);
                    if (power >= maxPower) {
                        maxPower = power;
                        maxCoord = x + "," + y + "," + squareSize;
                    }
                }
            }
        }
        return maxCoord;
    }
}
