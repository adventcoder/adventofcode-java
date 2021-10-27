package adventofcode.calendar.year2018.day7;

import adventofcode.framework.AbstractPart;

import java.util.*;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        Graph graph = new Graph(input);
        PriorityQueue<String> available = new PriorityQueue<>();
        for (String step : graph.steps()) {
            if (graph.inputs(step).isEmpty()) {
                available.add(step);
            }
        }
        List<String> steps = new ArrayList<>();
        while (!graph.steps().isEmpty()) {
            String step = available.remove();
            for (String output : graph.remove(step).outputs) {
                if (graph.inputs(output).isEmpty()) {
                    available.add(output);
                }
            }
            steps.add(step);
        }
        return String.join("", steps);
    }
}
