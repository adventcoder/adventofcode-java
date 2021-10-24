package adventofcode.calendar.year2019.day18;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Graph graph = new Graph(input.split("\n"));
        return graph.getAllKeys(new State('@', 0), graph.allKeys());
    }
}
