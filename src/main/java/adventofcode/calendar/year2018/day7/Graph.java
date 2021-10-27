package adventofcode.calendar.year2018.day7;

import java.util.*;

public class Graph {
    public static class Node {
        public final Set<String> inputs = new HashSet<>();
        public final Set<String> outputs = new HashSet<>();
    }

    private final Map<String, Node> nodes = new HashMap<>();

    public Graph(String input) {
        for (String line : input.split("\n")) {
            String[] words = line.split("\\s+");
            add(words[1], words[7]);
        }
    }

    public Set<String> steps() {
        return nodes.keySet();
    }

    public Set<String> inputs(String step) {
        return nodes.get(step).inputs;
    }

    public void add(String input, String output) {
        if (!nodes.containsKey(input)) nodes.put(input, new Node());
        nodes.get(input).outputs.add(output);
        if (!nodes.containsKey(output)) nodes.put(output, new Node());
        nodes.get(output).inputs.add(input);
    }

    public Node remove(String step) {
        Node node = nodes.remove(step);
        if (node != null) {
            for (String input : node.inputs) {
                nodes.get(input).outputs.remove(step);
            }
            for (String output : node.outputs) {
                nodes.get(output).inputs.remove(step);
            }
        }
        return node;
    }
}
