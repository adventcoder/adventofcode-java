package adventofcode.calendar.year2018.day7;

import adventofcode.framework.AbstractPart;

import java.util.*;

import static adventofcode.utils.Iterables.min;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Graph graph = new Graph(input);
        PriorityQueue<String> available = new PriorityQueue<>();
        for (String step : graph.steps()) {
            if (graph.inputs(step).isEmpty()) {
                available.add(step);
            }
        }
        Map<String, Integer> wip = new HashMap<>();
        int t = 0;
        while (!graph.steps().isEmpty()) {
            while (wip.size() < 5 && !available.isEmpty()) {
                String step = available.remove();
                wip.put(step, 61 + step.charAt(0) - 'A');
            }
            int dt = min(Comparator.naturalOrder(), wip.values());
            Iterator<String> it = wip.keySet().iterator();
            while (it.hasNext()) {
                String step = it.next();
                wip.put(step, wip.get(step) - dt);
                if (wip.get(step) == 0) {
                    it.remove();
                    for (String output : graph.remove(step).outputs) {
                        if (graph.inputs(output).isEmpty()) {
                            available.add(output);
                        }
                    }
                }
            }
            t += dt;
        }
        return t;
    }
}
