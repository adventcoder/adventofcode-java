package adventofcode.calendar.year2017.day6;

import adventofcode.framework.AbstractPart;

import java.util.*;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Banks banks = new Banks(input);
        Map<Banks, Integer> times = new HashMap<>();
        for (int time = 0; ; time++) {
            Integer lastTime = times.put(banks, time);
            if (lastTime != null) return time - lastTime;
            banks.redistribute();
        }
    }
}
