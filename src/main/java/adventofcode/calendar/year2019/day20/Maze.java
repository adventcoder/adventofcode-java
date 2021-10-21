package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

import java.util.*;

public class Maze {
    private static final List<Vector2D> dirs = Arrays.asList(new Vector2D(0, -1), new Vector2D(0, 1), new Vector2D(-1, 0), new Vector2D(1, 0));

    public final String[] grid;
    public final Map<String, Set<Vector2D>> labelPositions = new HashMap<>();

    public Maze(String input) {
        grid = input.split("\n");
        for (int y = 2; y < grid.length - 2; y++) {
            for (int x = 2; x < grid[y].length() - 2; x++) {
                String label = getLabel(x, y);
                if (label != null) {
                    if (!labelPositions.containsKey(label)) labelPositions.put(label, new HashSet<>());
                    labelPositions.get(label).add(new Vector2D(x, y));
                }
            }
        }
    }

    public boolean isInner(Vector2D pos) {
        return pos.y > 2 && pos.y < grid.length - 3 && pos.x > 2 && pos.x < grid[pos.y].length() - 3;
    }

    public Vector2D getOtherPosition(Vector2D pos) {
        String label = getLabel(pos.x, pos.y);
        if (label != null) {
            for (Vector2D otherPos : labelPositions.get(label)) {
                if (!otherPos.equals(pos)) {
                    return otherPos;
                }
            }
        }
        return null;
    }

    public Set<String> getLabels() {
        return labelPositions.keySet();
    }

    public Set<Vector2D> getLabelPositions(String label) {
        return labelPositions.getOrDefault(label, Collections.emptySet());
    }

    public Vector2D getLabelPosition(String label) {
        for (Vector2D pos : getLabelPositions(label)) {
            return pos;
        }
        return null;
    }

    public String getLabel(int x, int y) {
        if (grid[y].charAt(x) != '.') {
            return null;
        }
        if (Character.isLetter(grid[y].charAt(x - 1))) {
            return grid[y].substring(x - 2, x);
        }
        if (Character.isLetter(grid[y].charAt(x + 1))) {
            return grid[y].substring(x + 1, x + 3);
        }
        if (Character.isLetter(grid[y - 1].charAt(x))) {
            return Character.toString(grid[y - 2].charAt(x)) + grid[y - 1].charAt(x);
        }
        if (Character.isLetter(grid[y + 1].charAt(x))) {
            return Character.toString(grid[y + 1].charAt(x)) + grid[y + 2].charAt(x);
        }
        return null;
    }

    public Integer getDistance(Vector2D start, Vector2D end) {
        Map<Vector2D, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector2D pos = queue.remove();
            if (pos.equals(end)) {
                return distances.get(pos);
            } else {
                for (Vector2D dir : dirs) {
                    Vector2D newPos = pos.add(dir);
                    if (grid[newPos.y].charAt(newPos.x) == '.' && !distances.containsKey(newPos)) {
                        distances.put(newPos, distances.get(pos) + 1);
                        queue.add(newPos);
                    }
                }
                Vector2D newPos = getOtherPosition(pos);
                if (newPos != null && !distances.containsKey(newPos)) {
                    distances.put(newPos, distances.get(pos) + 1);
                    queue.add(newPos);
                }
            }
        }
        return null;
    }

    public Map<Vector2D, Integer> getAllWalkDistances(Vector2D start) {
        Map<Vector2D, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector2D pos = queue.remove();
            for (Vector2D dir : dirs) {
                Vector2D newPos = pos.add(dir);
                if (grid[newPos.y].charAt(newPos.x) == '.' && !distances.containsKey(newPos)) {
                    distances.put(newPos, distances.get(pos) + 1);
                    queue.add(newPos);
                }
            }
        }
        return distances;
    }
}
