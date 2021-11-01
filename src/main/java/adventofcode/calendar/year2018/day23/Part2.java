package adventofcode.calendar.year2018.day23;

import adventofcode.framework.AbstractPart;

import java.util.*;

import static adventofcode.utils.Iterables.*;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        List<Octahedron> signals = collect(Octahedron::parse, Arrays.asList(input.split("\n")));
        PriorityQueue<Map.Entry<Box, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        Box box = Box.bounding(signals);
        queue.add(new AbstractMap.SimpleImmutableEntry<>(box, -countIntersections(box, signals)));
        while (!queue.isEmpty()) {
            Map.Entry<Box, Integer> entry = queue.remove();
            box = entry.getKey();
            if (box.min.equals(box.max)) {
                return new Point(0, 0, 0).distanceTo(box.min);
            } else {
                for (Box subBox : box.split()) {
                    queue.add(new AbstractMap.SimpleImmutableEntry<>(subBox, -countIntersections(subBox, signals)));
                }
            }
        }
        return null;
    }

    private Integer countIntersections(Box box, List<Octahedron> signals) {
        return tally((signal) -> signal.intersects(box), signals);
    }
}
