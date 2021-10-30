package adventofcode.calendar.year2018.day18;

import adventofcode.framework.AbstractPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends AbstractPart<Integer> {
    private static final int targetTime = 1000000000;

    @Override
    public Integer solve(String input) {
        Map<State, Integer> seen = new HashMap<>();
        List<State> states = new ArrayList<>();
        State state = State.parse(input);
        int time = 0;
        while (true) {
            Integer lastTime = seen.put(state, time);
            if (lastTime != null) {
                return states.get(lastTime + (targetTime - lastTime) % (time - lastTime)).resourceValue();
            }
            states.add(state);
            state = state.tick();
            time++;
        }
    }
}
