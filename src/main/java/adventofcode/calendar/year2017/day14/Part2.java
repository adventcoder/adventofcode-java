package adventofcode.calendar.year2017.day14;

import adventofcode.calendar.year2017.day10.KnotHash;
import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        byte[][] grid = new byte[128][];
        for (int y = 0; y < 128; y++) {
            grid[y] = KnotHash.standard(input + "-" + y).toBitArray();
        }
        int count = 0;
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                if (grid[y][x] == 1) {
                    clearUsed(grid, x, y);
                    count++;
                }
            }
        }
        return count;
    }

    private void clearUsed(byte[][] grid, int x, int y) {
        if (grid[y][x] == 0) return;
        grid[y][x] = 0;
        if (y > 0) clearUsed(grid, x, y - 1);
        if (y < 127) clearUsed(grid, x, y + 1);
        if (x > 0) clearUsed(grid, x - 1, y);
        if (x < 127) clearUsed(grid, x + 1, y);
    }
}
