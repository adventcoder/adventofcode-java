package adventofcode.calendar.year2018.day20;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import java.util.Map;

import static adventofcode.utils.Iterables.tally;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Maze maze = new Maze(input, new Vector2D(0, 0));
        Map<Vector2D, Integer> distances = maze.getDistances(new Vector2D(0, 0));
        return tally((distance) -> distance >= 1000, distances.values());
    }
}
