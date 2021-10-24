package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.Consumer;

public class Maze {
    private static final List<Vector2D> dirs = Arrays.asList(new Vector2D(0, -1), new Vector2D(0, 1), new Vector2D(-1, 0), new Vector2D(1, 0));

    private final String[] grid;
    private final Map<Portal, Vector2D> portalPositions = new HashMap<>();

    public Maze(String input) {
        this.grid = input.split("\n");
        for (int y = 2; y < grid.length - 2; y++) {
            for (int x = 2; x < grid[y].length() - 2; x++) {
                Portal portal = getPortal(x, y);
                if (portal != null) {
                    portalPositions.put(portal, new Vector2D(x, y));
                }
            }
        }
    }

    public Vector2D getPortalPosition(Portal portal) {
        return portalPositions.get(portal);
    }

    public Portal getPortal(int x, int y) {
        String label = getLabel(x, y);
        if (label != null) {
            return new Portal(label, isInside(x, y));
        }
        return null;
    }

    public boolean isInside(int x, int y) {
        return y > 2 && y < grid.length - 3 && x > 2 && x < grid[y].length() - 3;
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

    public boolean isWalkable(Vector2D pos) {
        return grid[pos.y].charAt(pos.x) == '.';
    }

    public void forEachNeighbour(Vector2D pos, Consumer<Vector2D> action) {
        for (Vector2D dir : dirs) {
            Vector2D newPos = pos.add(dir);
            if (isWalkable(newPos)) {
                action.accept(pos.add(dir));
            }
        }
        Portal portal = getPortal(pos.x, pos.y);
        if (portal != null) {
            Vector2D newPos = portalPositions.get(portal.other());
            if (newPos != null) {
                action.accept(newPos);
            }
        }
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
            }
            forEachNeighbour(pos, (newPos) -> {
                if (!distances.containsKey(newPos)) {
                    distances.put(newPos, distances.get(pos) + 1);
                    queue.add(newPos);
                }
            });
        }
        return null;
    }

    public Integer getHyperDistance(Portal start, Portal end) {
        return toHyperMaze().getDistance(new HyperPortal(start, 0), new HyperPortal(end, 0));
    }

    public HyperMaze toHyperMaze() {
        Map<Portal, Map<Portal, Integer>> edges = new HashMap<>();
        for (Portal start : portalPositions.keySet()) {
            edges.put(start, new HashMap<>());
            Map<Vector2D, Integer> distances = new HashMap<>();
            distances.put(portalPositions.get(start), 0);
            Queue<Vector2D> queue = new ArrayDeque<>();
            queue.add(portalPositions.get(start));
            while (!queue.isEmpty()) {
                Vector2D pos = queue.remove();
                Portal end = getPortal(pos.x, pos.y);
                if (end != null && !start.equals(end)) {
                    edges.get(start).put(end, distances.get(pos));
                } else {
                    for (Vector2D dir : dirs) {
                        Vector2D newPos = pos.add(dir);
                        if (isWalkable(newPos) && !distances.containsKey(newPos)) {
                            queue.add(newPos);
                            distances.put(newPos, distances.get(pos) + 1);
                        }
                    }
                }
            }
        }
        return new HyperMaze(edges);
    }
}
