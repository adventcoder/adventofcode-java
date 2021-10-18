package adventofcode.calendar.year2019.day17;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        BufferedIntcode terminal = new BufferedIntcode(input);
        String[] grid = terminal.readLines();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (isIntersection(grid, x, y)) {
                    sum += x * y;
                }
            }
        }
        return sum;
    }

    private boolean isIntersection(String[] grid, int x, int y) {
        return isScaffold(grid, x, y) &&
                isScaffold(grid, x - 1, y) &&
                isScaffold(grid, x + 1, y) &&
                isScaffold(grid, x, y - 1) &&
                isScaffold(grid, x, y + 1);
    }

    private boolean isScaffold(String[] grid, int x, int y) {
        return y >= 0 && y < grid.length &&
                x >= 0 && x < grid[y].length() &&
                grid[y].charAt(x) == '#';
    }
}
