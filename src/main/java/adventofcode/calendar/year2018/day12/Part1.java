package adventofcode.calendar.year2018.day12;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] chunks = input.split("\n\n");
        State state = State.parseState(chunks[0].substring("initial state: ".length()));
        int rules = State.parseRules(chunks[1]);
        for (int t = 0; t < 20; t++) {
            state = state.tick(rules);
        }
        return state.sum();
    }
}
