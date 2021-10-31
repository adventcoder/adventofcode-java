package adventofcode.calendar.year2018.day20;

import adventofcode.utils.Range;
import adventofcode.utils.Vector2D;

import java.util.*;

public class Maze {
    private final Map<Vector2D, Set<Vector2D>> neighbours = new HashMap<>();

    public Maze(String input, Vector2D start) {
        Regex.compile(input).addDoors(Collections.singleton(start), this);
    }

    public void addDoor(Vector2D a, Vector2D b) {
        if (!neighbours.containsKey(a)) neighbours.put(a, new HashSet<>());
        neighbours.get(a).add(b);
        if (!neighbours.containsKey(b)) neighbours.put(b, new HashSet<>());
        neighbours.get(b).add(a);
    }

    @Override
    public String toString() {
        Range xs = Range.empty();
        Range ys = Range.empty();
        for (Vector2D room : neighbours.keySet()) {
            xs.add(room.x);
            ys.add(room.y);
        }
        StringBuilder[] lines = new StringBuilder[ys.size() * 2 + 1];
        for (int y = 0; y < lines.length; y++) {
            lines[y] = new StringBuilder();
            for (int x = 0; x < xs.size() * 2 + 1; x++) {
                lines[y].append('#');
            }
        }
        for (Vector2D room : neighbours.keySet()) {
            int x = room.x - xs.min;
            int y = room.y - ys.min;
            lines[2 * y + 1].setCharAt(2 * x + 1, '.');
            if (neighbours.get(room).contains(room.up())) {
                lines[2 * y].setCharAt(2 * x + 1, '-');
            }
            if (neighbours.get(room).contains(room.down())) {
                lines[2 * y + 2].setCharAt(2 * x + 1, '-');
            }
            if (neighbours.get(room).contains(room.left())) {
                lines[2 * y + 1].setCharAt(2 * x, '|');
            }
            if (neighbours.get(room).contains(room.right())) {
                lines[2 * y + 1].setCharAt(2 * x + 2, '|');
            }
        }
        lines[1 - 2 * ys.min].setCharAt(1 - 2 * xs.min, 'X');
        return String.join("\n", lines);
    }

    public Map<Vector2D, Integer> getDistances(Vector2D start) {
        Map<Vector2D, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector2D room = queue.remove();
            for (Vector2D newRoom : neighbours.get(room)) {
                if (!distances.containsKey(newRoom)) {
                    distances.put(newRoom, distances.get(room) + 1);
                    queue.add(newRoom);
                }
            }
        }
        return distances;
    }
}
