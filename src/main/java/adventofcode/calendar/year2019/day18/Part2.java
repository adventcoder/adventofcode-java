package adventofcode.calendar.year2019.day18;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static adventofcode.utils.Iterables.max;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] grid = input.split("\n");
        int width = max(String::length, Arrays.asList(grid));
        update(grid, width);
        int totalSteps = 0;
        for (Graph graph : split(grid, width)) {
            totalSteps += graph.getAllKeys(new State('@', ~graph.allKeys()), graph.allKeys());
        }
        return totalSteps;
    }

    private void update(String[] grid, int width) {
        int x = width / 2;
        int y = grid.length / 2;
        update(grid, x - 1, y - 1, "@#@");
        update(grid, x - 1, y,     "###");
        update(grid, x - 1, y + 1, "@#@");
    }

    private void update(String[] grid, int x, int y, String sub) {
        grid[y] = grid[y].substring(0, x) + sub + grid[y].substring(x + sub.length());
    }

    private List<Graph> split(String[] grid, int width) {
        List<Graph> graphs = new ArrayList<>();
        Vector2D min = new Vector2D(0, 0);
        Vector2D mid = new Vector2D(width / 2, grid.length / 2);
        Vector2D max = new Vector2D(width - 1, grid.length - 1);
        graphs.add(new Graph(grid, min, mid));
        graphs.add(new Graph(grid, new Vector2D(mid.x, min.y), new Vector2D(max.x, mid.y)));
        graphs.add(new Graph(grid, new Vector2D(min.x, mid.y), new Vector2D(mid.x, max.y)));
        graphs.add(new Graph(grid, mid, max));
        return graphs;
    }
}
