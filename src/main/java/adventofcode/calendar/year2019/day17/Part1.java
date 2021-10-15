package adventofcode.calendar.year2019.day17;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        BufferedIntcode terminal = new BufferedIntcode(input);
        List<String> grid = terminal.readLines();
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(y).length(); x++) {
                if (isIntersection(grid, x, y)) {
                    sum += x * y;
                }
            }
        }
        return sum;
    }

    private boolean isIntersection(List<String> grid, int x, int y) {
        return isScaffold(grid, x, y) &&
                isScaffold(grid, x - 1, y) &&
                isScaffold(grid, x + 1, y) &&
                isScaffold(grid, x, y - 1) &&
                isScaffold(grid, x, y + 1);
    }

    private boolean isScaffold(List<String> grid, int x, int y) {
        return y >= 0 && y < grid.size() && x >= 0 && x < grid.get(y).length() && grid.get(y).charAt(x) == '#';
    }
}
