package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.BiConsumer;

public class HyperMaze {
    private final Map<Portal, Map<Portal, Integer>> distances = new HashMap<>();

    public HyperMaze(String input) {
        this(new Maze(input));
    }

    public HyperMaze(Maze maze) {
        for (Portal from : maze.portals()) {
            distances.put(from, new HashMap<>());
            Map<Vector2D, Integer> toDistances = maze.getDistances(maze.getPortalPosition(from), false);
            for (Portal to : maze.portals()) {
                if (toDistances.containsKey(maze.getPortalPosition(to))) {
                    distances.get(from).put(to, toDistances.get(maze.getPortalPosition(to)));
                }
            }
        }
    }

    public Integer getDistance(HyperPortal start, HyperPortal end) {
        Set<HyperPortal> closed = new HashSet<>();
        PriorityQueue<Map.Entry<HyperPortal, Integer>> open = new PriorityQueue<>(Map.Entry.comparingByValue());
        open.add(new AbstractMap.SimpleEntry<>(start, 0));
        while (!open.isEmpty()) {
            Map.Entry<HyperPortal, Integer> entry = open.remove();
            HyperPortal pos = entry.getKey();
            int distance = entry.getValue();
            if (!closed.add(pos)) continue;
            if (pos.equals(end)) {
                return distance;
            }
            forEachNeighbour(pos, distance, (newPos, newDistance) -> {
                open.add(new AbstractMap.SimpleEntry<>(newPos, newDistance));
            });
        }
        return null;
    }

    public void forEachNeighbour(HyperPortal pos, int distance, BiConsumer<HyperPortal, Integer> action) {
        for (Portal newPortal : distances.get(pos.portal).keySet()) {
            int newDistance = distance + distances.get(pos.portal).get(newPortal);
            if (distances.containsKey(newPortal.other())) {
                int newFloor = pos.floor + (newPortal.inside ? 1 : -1);
                if (newFloor >= 0) {
                    action.accept(new HyperPortal(newPortal.other(), newFloor), newDistance + 1);
                }
            } else {
                action.accept(new HyperPortal(newPortal, pos.floor), newDistance);
            }
        }
    }
}
