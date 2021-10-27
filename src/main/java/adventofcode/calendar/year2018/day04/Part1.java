package adventofcode.calendar.year2018.day04;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Range;

import java.util.*;

import static adventofcode.utils.Iterables.*;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<Integer, int[]> sleepFrequency = new HashMap<>();
        Map<Integer, Integer> sleepTotal = new HashMap<>();
        Log.forEachNap(input, (id, minutes) -> {
            sleepTotal.put(id, sleepTotal.getOrDefault(id, 0) + minutes.size());
            if (!sleepFrequency.containsKey(id)) {
                sleepFrequency.put(id, new int[60]);
            }
            for (int minute : minutes) {
                sleepFrequency.get(id)[minute]++;
            }
        });
        Integer guardId = argMax(sleepTotal::get, sleepTotal.keySet());
        Integer minute = argMax((min) -> sleepFrequency.get(guardId)[min], Range.exclusive(60));
        return guardId * minute;
    }
}
