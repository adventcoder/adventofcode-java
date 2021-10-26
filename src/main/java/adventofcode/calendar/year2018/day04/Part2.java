package adventofcode.calendar.year2018.day04;

import adventofcode.framework.AbstractPart;

import java.util.HashMap;
import java.util.Map;

import static adventofcode.utils.Iterables.argMax;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<Integer, Integer> sleepFrequency = new HashMap<>();
        Log.forEachNap(input, (id, minutes) -> {
            for (int minute : minutes) {
                sleepFrequency.put(id * minute, sleepFrequency.getOrDefault(id * minute, 0) + 1);
            }
        });
        return argMax(sleepFrequency::get, sleepFrequency.keySet());
    }
}
