package adventofcode.calendar.year2019.day24;

import adventofcode.framework.AbstractPart;

import java.util.HashMap;
import java.util.Map;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<Integer, Integer> states = new HashMap<>();
        states.put(0, State.parse(input));
        for (int t = 0; t < 200; t++) {
            Map<Integer, Integer> newStates = new HashMap<>();
            for (int z : states.keySet()) {
                if (states.get(z) == 0) continue;
                tickState(newStates, states, z - 1);
                tickState(newStates, states, z);
                tickState(newStates, states, z + 1);
            }
            states = newStates;
        }
        int count = 0;
        for (int z : states.keySet()) {
            if (states.get(z) == 0) continue;
            count += Integer.bitCount(states.get(z));
        }
        return count;
    }

    private void tickState(Map<Integer, Integer> newStates, Map<Integer, Integer> states, int z) {
        if (newStates.containsKey(z)) return;
        newStates.put(z, State.tick(states.getOrDefault(z - 1, 0), states.getOrDefault(z, 0), states.getOrDefault(z + 1, 0)));
    }
}
