package adventofcode.calendar.year2018.day12;

import adventofcode.framework.AbstractPart;

import java.util.Arrays;

public class Part2 extends AbstractPart<Long> {
    private static final long targetTime = 50000000000L;

    @Override
    public Long solve(String input) {
        String[] chunks = input.split("\n\n");
        State state = State.parseState(chunks[0].substring("initial state: ".length()));
        int rules = State.parseRules(chunks[1]);
        for (int time = 0; ; time++) {
            State newState = state.tick(rules);
            if (Arrays.equals(state.state, newState.state)) {
                long targetMin = state.min + (targetTime - time) * (newState.min - state.min);
                return state.sum() + (targetMin - state.min) * state.count();
            }
            state = newState;
        }
    }
}
