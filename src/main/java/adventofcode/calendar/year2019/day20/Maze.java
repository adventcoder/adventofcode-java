package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.Consumer;

public class Maze {
    private final String[] grid;
    private final Map<Portal, Vector2D> portals = new HashMap<>();

    public Maze(String input) {
        this.grid = input.split("\n");
        for (int y = 2; y < grid.length - 2; y++) {
            for (int x = 2; x < grid[y].length() - 2; x++) {
                Portal portal = getPortal(x, y);
                if (portal != null) {
                    portals.put(portal, new Vector2D(x, y));
                }
            }
        }
    }

    public Set<Portal> portals() {
        return portals.keySet();
    }

    public Vector2D getPortalPosition(Portal portal) {
        return portals.get(portal);
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
        if (isSpace(x, y)) {
            if (Character.isLetter(grid[y].charAt(x - 1))) {
                return grid[y].substring(x - 2, x);
            } else if (Character.isLetter(grid[y].charAt(x + 1))) {
                return grid[y].substring(x + 1, x + 3);
            } else if (Character.isLetter(grid[y - 1].charAt(x))) {
                return Character.toString(grid[y - 2].charAt(x)) + grid[y - 1].charAt(x);
            } else if (Character.isLetter(grid[y + 1].charAt(x))) {
                return Character.toString(grid[y + 1].charAt(x)) + grid[y + 2].charAt(x);
            }
        }
        return null;
    }

    public boolean isSpace(int x, int y) {
        return grid[y].charAt(x) == '.';
    }

    public Integer getDistance(Portal start, Portal end) {
        return getDistance(getPortalPosition(start), getPortalPosition(end), true);
    }

    public Integer getDistance(Vector2D start, Vector2D end, boolean portals) {
        Map<Vector2D, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector2D pos = queue.remove();
            if (pos.equals(end)) {
                return distances.get(pos);
            }
            forEachNeighbour(pos, portals, (newPos) -> {
                if (!distances.containsKey(newPos)) {
                    distances.put(newPos, distances.get(pos) + 1);
                    queue.add(newPos);
                }
            });
        }
        return null;
    }

    public Map<Vector2D, Integer> getDistances(Vector2D start, boolean portals) {
        Map<Vector2D, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector2D pos = queue.remove();
            forEachNeighbour(pos, portals, (newPos) -> {
                if (!distances.containsKey(newPos)) {
                    distances.put(newPos, distances.get(pos) + 1);
                    queue.add(newPos);
                }
            });
        }
        return distances;
    }

    public void forEachNeighbour(Vector2D pos, boolean includePortals, Consumer<Vector2D> action) {
        for (Vector2D newPos : pos.neighbours()) {
            if (isSpace(newPos.x, newPos.y)) {
                action.accept(newPos);
            }
        }
        if (includePortals) {
            Portal portal = getPortal(pos.x, pos.y);
            if (portal != null) {
                Vector2D newPos = this.portals.get(portal.other());
                if (newPos != null) {
                    action.accept(newPos);
                }
            }
        }
    }
}
