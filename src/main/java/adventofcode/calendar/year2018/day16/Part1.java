package adventofcode.calendar.year2018.day16;

import adventofcode.framework.AbstractPart;

import java.util.ArrayList;
import java.util.List;

import static adventofcode.utils.Iterables.tally;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] chunks = input.split("\n\n\n\n");
        List<Sample> samples = new ArrayList<>();
        for (String chunk : chunks[0].split("\n\n")) {
            samples.add(new Sample(chunk));
        }
        return tally((sample) -> hits(sample) >= 3, samples);
    }

    private int hits(Sample sample) {
        return tally(sample::test, Operator.values);
    }
}
