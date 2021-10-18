package adventofcode.calendar.year2019.day24;

import adventofcode.framework.AbstractPart;

import java.util.HashSet;
import java.util.Set;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int state = State.parse(input);
        Set<Integer> states = new HashSet<>();
        while (states.add(state)) {
            state = State.tick(state);
        }
        return state;
    }
}
