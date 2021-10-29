package adventofcode.calendar.year2018.day15;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    private static final boolean verbose = false;

    @Override
    public Integer solve(String input) {
        Combat combat = new Combat(input);
        int rounds = 0;
        if (verbose) {
            System.out.println("Initially:");
            System.out.println(combat);
        }
        while (combat.completeRound()) {
            rounds++;
            if (verbose) {
                System.out.println();
                System.out.println("After " + rounds + " rounds:");
                System.out.println(combat);
            }
        }
        if (verbose) {
            System.out.println();
            System.out.println("Finally:");
            System.out.println(combat);
        }
        return rounds * combat.totalHp();
    }
}
