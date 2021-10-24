package adventofcode.calendar.year2019.day03;

import adventofcode.framework.AbstractPart;

import java.util.stream.Stream;

import static adventofcode.utils.Iterables.min;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Wire[] wires = Stream.of(input.split("\n")).map(Wire::new).toArray(Wire[]::new);
        return min((p) -> p.distance, wires[0].cross(wires[1]));
    }
}
