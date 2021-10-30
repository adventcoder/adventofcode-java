package adventofcode.calendar.year2018.day18;

import java.util.Arrays;

import static adventofcode.utils.Iterables.map;

public class State {
    private final char[][] grid;

    private State(char[][] grid) {
        this.grid = grid;
    }

    public static State parse(String input) {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][];
        for (int y = 0; y < grid.length; y++) {
            grid[y] = lines[y].toCharArray();
        }
        return new State(grid);
    }

    public State tick() {
        char[][] newGrid = new char[grid.length][];
        for (int y = 0; y < newGrid.length; y++) {
            newGrid[y] = new char[grid[y].length];
            for (int x = 0; x < newGrid[y].length; x++) {
                newGrid[y][x] = tick(x, y);
            }
        }
        return new State(newGrid);
    }

    public char tick(int x, int y) {
        if (grid[y][x] == '.' && count(x, y, '|') >= 3) {
            return '|';
        } else if (grid[y][x] == '|' && count(x, y, '#') >= 3) {
            return '#';
        } else if (grid[y][x] == '#' && (count(x, y, '#') == 1 || count(x, y, '|') == 0)) {
            return '.';
        } else {
            return grid[y][x];
        }
    }

    public int count(int x, int y, char c) {
        int yMin = Math.max(y - 1, 0);
        int yMax = Math.min(y + 1, grid.length - 1);
        int xMin = Math.max(x - 1, 0);
        int xMax = Math.min(x + 1, grid[y].length - 1);
        char count = 0;
        for (y = yMin; y <= yMax; y++) {
            for (x = xMin; x <= xMax; x++) {
                if (grid[y][x] == c) count++;
            }
        }
        return count;
    }

    public int resourceValue() {
        int treeCount = 0;
        int yardCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '|') treeCount++;
                else if (grid[y][x] == '#') yardCount++;
            }
        }
        return treeCount * yardCount;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof State && Arrays.deepEquals(grid, ((State) obj).grid);
    }

    @Override
    public String toString() {
        return String.join("\n", map(String::new, Arrays.asList(grid)));
    }
}

