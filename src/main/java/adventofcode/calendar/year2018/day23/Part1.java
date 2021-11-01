package adventofcode.calendar.year2018.day23;

import adventofcode.framework.AbstractPart;

import java.util.Arrays;
import java.util.List;

import static adventofcode.utils.Iterables.*;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        List<Octahedron> signals = collect(Octahedron::parse, Arrays.asList(input.split("\n")));
        Octahedron strongestSignal = argMax((bot) -> bot.radius, signals);
        return tally((signal) -> strongestSignal.contains(signal.pos), signals);
    }
}
