package adventofcode.calendar.year2018.day20;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import java.util.Comparator;
import java.util.Map;

import static adventofcode.utils.Iterables.max;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        //TODO: this one feels kind of slow and clunky. Could probably be improved.
        Maze maze = new Maze(input, new Vector2D(0, 0));
        // System.out.println(maze);
        Map<Vector2D, Integer> distances = maze.getDistances(new Vector2D(0, 0));
        return max(Comparator.naturalOrder(), distances.values());
    }
}
