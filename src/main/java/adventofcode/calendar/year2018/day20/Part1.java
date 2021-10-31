package adventofcode.calendar.year2018.day20;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import java.util.*;

import static adventofcode.utils.Iterables.max;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Maze maze = new Maze(input, new Vector2D(0, 0));
        // System.out.println(maze);
        Map<Vector2D, Integer> distances = maze.getDistances(new Vector2D(0, 0));
        return max(Comparator.naturalOrder(), distances.values());
    }
}
