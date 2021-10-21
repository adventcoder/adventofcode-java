package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.BiConsumer;

public class RecursiveMaze {
    private final Maze maze;
    private final Map<Vector2D, Map<Vector2D, Integer>> walkDistances = new HashMap<>();

    public RecursiveMaze(String input) {
        this.maze = new Maze(input);
        for (String startLabel : maze.getLabels()) {
            for (Vector2D start : maze.getLabelPositions(startLabel)) {
                walkDistances.put(start, new HashMap<>());
                Map<Vector2D, Integer> allWalkDistances = maze.getAllWalkDistances(start);
                for (String endLabel : maze.getLabels()) {
                    for (Vector2D end : maze.getLabelPositions(endLabel)) {
                        if (!start.equals(end) && allWalkDistances.containsKey(end)) {
                            walkDistances.get(start).put(end, allWalkDistances.get(end));
                        }
                    }
                }
            }
        }
    }

    public HyperPoint getLabelPosition(String label) {
        return new HyperPoint(maze.getLabelPosition(label), 0);
    }

    public void forEachNeighbour(HyperPoint point, BiConsumer<HyperPoint, Integer> action) {
        for (Vector2D newPos : walkDistances.get(point.pos).keySet()) {
            int distance = walkDistances.get(point.pos).get(newPos);
            if (maze.getOtherPosition(newPos) != null) {
                int newFloor = point.floor + (maze.isInner(newPos) ? 1 : -1);
                if (newFloor >= 0) {
                    action.accept(new HyperPoint(maze.getOtherPosition(newPos), newFloor), distance + 1);
                }
            } else {
                action.accept(new HyperPoint(newPos, point.floor), distance);
            }
        }
    }

    public Integer findDistance(HyperPoint start, HyperPoint end) {
        Map<HyperPoint, Integer> closedDistances = new HashMap<>();
        Map<HyperPoint, Integer> openDistances = new HashMap<>();
        PriorityQueue<HyperPoint> open = new PriorityQueue<>(Comparator.comparing(openDistances::get));
        openDistances.put(start, 0);
        open.add(start);
        while (!open.isEmpty()) {
            HyperPoint pos = open.remove();
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
