package adventofcode.calendar.year2017.day6;

import adventofcode.framework.AbstractPart;

import java.util.*;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Banks banks = new Banks(input);
        Set<Banks> seen = new HashSet<>();
        while (seen.add(banks)) {
            banks.redistribute();
        }
        return seen.size();
    }
}
