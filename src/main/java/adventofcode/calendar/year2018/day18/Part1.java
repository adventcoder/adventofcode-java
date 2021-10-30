package adventofcode.calendar.year2018.day18;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    private static final boolean verbose = false;

    @Override
    public Integer solve(String input) {
        State state = State.parse(input);
        if (verbose) {
            System.out.println("Initial state:");
            System.out.println(state);
        }
        for (int t = 1; t <= 10; t++) {
            state = state.tick();
            if (verbose) {
                System.out.println();
                System.out.println("After " + t + " minutes:");
                System.out.println(state);
            }
        }
        return state.resourceValue();
    }
}
