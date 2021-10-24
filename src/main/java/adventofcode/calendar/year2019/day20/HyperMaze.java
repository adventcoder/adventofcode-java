package adventofcode.calendar.year2019.day20;

import java.util.*;
import java.util.function.BiConsumer;

public class HyperMaze {
    private final Map<Portal, Map<Portal, Integer>> edges;

    public HyperMaze(Map<Portal, Map<Portal, Integer>> edges) {
        this.edges = edges;
    }

    public void forEachNeighbour(HyperPortal pos, BiConsumer<HyperPortal, Integer> action) {
        for (Portal newPortal : edges.get(pos.portal).keySet()) {
            int distance = edges.get(pos.portal).get(newPortal);
            if (edges.containsKey(newPortal.other())) {
                int newFloor = pos.floor + (newPortal.inside ? 1 : -1);
                if (newFloor >= 0) {
                    action.accept(new HyperPortal(newPortal.other(), newFloor), distance + 1);
                }
            } else {
                action.accept(new HyperPortal(newPortal, pos.floor), distance);
            }
        }
    }

    public Integer getDistance(HyperPortal start, HyperPortal end) {
        Map<HyperPortal, Integer> closedDistances = new HashMap<>();
        Map<HyperPortal, Integer> openDistances = new HashMap<>();
        PriorityQueue<HyperPortal> open = new PriorityQueue<>(Comparator.comparing(openDistances::get));
        openDistances.put(start, 0);
        open.add(start);
        while (!open.isEmpty()) {
            HyperPortal pos = open.remove();
            closedDistances.put(pos, openDistances.remove(pos));
            // System.out.println("Closed " + pos + ", distance: " + closedDistances.get(pos));
            if (pos.equals(end)) {
                return closedDistances.get(pos);
            }
            forEachNeighbour(pos, (newPos, diff) -> {
                if (closedDistances.containsKey(newPos)) return;
                int distance = closedDistances.get(pos) + diff;
                if (!openDistances.containsKey(newPos) || distance < openDistances.get(newPos)) {
                    if (openDistances.containsKey(newPos)) open.remove(newPos);
                    // System.out.println("Adding " + newPos);
                    openDistances.put(newPos, distance);
                    open.add(newPos);
                }
            });
        }
        return null;
    }
}
