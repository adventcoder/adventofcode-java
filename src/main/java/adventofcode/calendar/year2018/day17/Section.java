package adventofcode.calendar.year2018.day17;

import adventofcode.utils.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static adventofcode.utils.Iterables.map;

public class Section {
    public final int x;
    public final int y;
    private final char[][] grid;

    public Section(String input) {
        Range[] bounds = { Range.empty(), Range.empty() };
        List<Range[]> entries = new ArrayList<>();
        for (String line : input.split("\n")) {
            Range[] entry = new Range[2];
            for (String token : line.split("\\s*,\\s*")) {
                String[] pair = token.split("=", 2);
                Range range = parseValue(pair[1]);
                if (pair[0].trim().equals("x")) {
                    entry[0] = range;
                    bounds[0].add(range.min - 1);
                    bounds[0].add(range.max + 1);
                } else if (pair[0].trim().equals("y")) {
                    entry[1] = range;
                    bounds[1].addAll(range);
                }
            }
            entries.add(entry);
        }
        x = bounds[0].min;
        y = bounds[1].min;
        grid = new char[bounds[1].size()][bounds[0].size()];
        for (int y = 0; y < grid.length; y++) {
            Arrays.fill(grid[y], '.');
        }
        for (Range[] entry : entries) {
            for (int y = entry[1].min; y <= entry[1].max; y++) {
                for (int x = entry[0].min; x <= entry[0].max; x++) {
                    grid[y - bounds[1].min][x - bounds[0].min] = '#';
                }
            }
        }
    }

    private static Range parseValue(String value) {
        int i = value.indexOf("..");
        if (i == -1) {
            return new Range(Integer.parseInt(value));
        } else {
            return new Range(Integer.parseInt(value.substring(0, i)), Integer.parseInt(value.substring(i + 2)));
        }
    }

    @Override
    public String toString() {
        return String.join("\n", map(String::new, Arrays.asList(grid)));
    }

    public int count(char tile) {
        int count = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == tile) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean fill(int x, int y) {
        int y0 = y;
        grid[y][x] = '|';
        while (true) {
            if (y + 1 == grid.length || grid[y + 1][x] == '|') return false;
            if (isFilled(x, y + 1)) break;
            grid[++y][x] = '|';
        }
        while (y >= y0) {
            Integer x1 = fillLeft(x, y);
            Integer x2 = fillRight(x, y);
            if (x1 == null || x2 == null) return false;
            Arrays.fill(grid[y], x1, x2 + 1, '~');
            y--;
        }
        return true;
    }

    private Integer fillLeft(int x, int y) {
        while (grid[y][x - 1] != '#') {
            grid[y][--x] = '|';
            if (!isFilled(x, y + 1) && !fill(x, y + 1)) return null;
        }
        return x;
    }

    private Integer fillRight(int x, int y) {
        while (grid[y][x + 1] != '#') {
            grid[y][++x] = '|';
            if (!isFilled(x, y + 1) && !fill(x, y + 1)) return null;
        }
        return x;
    }

    private boolean isFilled(int x, int y) {
        return grid[y][x] == '#' || grid[y][x] == '~';
    }
}
