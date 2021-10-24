package adventofcode.calendar.year2019.day18;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.BiConsumer;

import static adventofcode.utils.Iterables.max;

public class Graph {
    private static final List<Vector2D> dirs = Arrays.asList(new Vector2D(0, -1), new Vector2D(0, 1), new Vector2D(-1, 0), new Vector2D(1, 0));

    private Map<Character, Vector2D> nodes = new HashMap<>();
    private Map<Character, Map<Character, Edge>> edges = new HashMap<>();

    public Graph(String[] grid) {
        this(grid, new Vector2D(0, 0), new Vector2D(max(String::length, Arrays.asList(grid)) - 1, grid.length - 1));
    }

    public Graph(String[] grid, Vector2D min, Vector2D max) {
        for (int y = min.y; y <= max.y; y++) {
            for (int x = min.x; x <= max.x; x++) {
                char c = grid[y].charAt(x);
                if (c == '@' || (c >= 'a' && c <= 'z')) {
                    nodes.put(c, new Vector2D(x, y));
                }
            }
        }
        for (char from : nodes.keySet()) {
            edges.put(from, new HashMap<>());
            Map<Vector2D, Edge> toEdges = getEdges(grid, nodes.get(from));
            for (char to : nodes.keySet()) {
                if (toEdges.containsKey(nodes.get(to))) {
                    edges.get(from).put(to, toEdges.get(nodes.get(to)));
                }
            }
        }
    }

    private static Map<Vector2D, Edge> getEdges(String[] grid, Vector2D from) {
        Map<Vector2D, Edge> edges = new HashMap<>();
        edges.put(from, new Edge(0, 0, 0));
        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(from);
        while (!queue.isEmpty()) {
            Vector2D pos = queue.remove();
            for (Vector2D dir : dirs) {
                Vector2D newPos = pos.add(dir);
                if (getChar(grid, newPos) == '#') continue;
                if (edges.containsKey(newPos)) continue;
                edges.put(newPos, edges.get(pos).add(getChar(grid, newPos)));
                queue.add(newPos);
            }
        }
        return edges;
    }

    private static char getChar(String[] grid, Vector2D pos) {
        return grid[pos.y].charAt(pos.x);
    }

    public int allKeys() {
        int keys = 0;
        for (char c : nodes.keySet()) {
            if (c >= 'a' && c <= 'z') {
                keys |= 1 << (c - 'a');
            }
        }
        return keys;
    }

    public void forEachNeighbour(State state, int steps, BiConsumer<State, Integer> action) {
        for (char node : edges.get(state.node).keySet()) {
            Edge edge = edges.get(state.node).get(node);
            if (!state.hasAllKeys(edge.doors)) continue; // don't have all required keys
            if (state.hasAllKeys(edge.keys)) continue; // already have all keys
            action.accept(new State(node, state.keys | edge.keys), steps + edge.steps);
        }
    }

    public Integer getAllKeys(State start, int keys) {
        Set<State> seen = new HashSet<>();
        PriorityQueue<Map.Entry<State, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        queue.add(new AbstractMap.SimpleEntry<>(start, 0));
        while (!queue.isEmpty()) {
            Map.Entry<State, Integer> entry = queue.remove();
            State state = entry.getKey();
            int steps = entry.getValue();
            // System.out.println("Got " + state + " after " + steps + " steps");
            if (!seen.add(state)) continue;
            if (state.hasAllKeys(keys)) {
                return steps;
            }
            forEachNeighbour(state, steps, (newState, newSteps) -> {
                queue.add(new AbstractMap.SimpleEntry<>(newState, newSteps));
            });
        }
        return null;
    }
}
