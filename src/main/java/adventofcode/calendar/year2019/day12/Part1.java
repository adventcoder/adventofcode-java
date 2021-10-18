package adventofcode.calendar.year2019.day12;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    private static final boolean verbose = false;
    private static final int targetTime = 1000;

    @Override
    public Integer solve(String input) {
        MoonSystem system = new MoonSystem(input);
        for (int time = 0; time < targetTime; time++) {
            if (verbose) {
                System.out.println("After " + time + " steps:");
                system.print();
                System.out.println();
            }
            system.tick();
        }
        if (verbose) {
            System.out.println("After " + targetTime + " steps:");
            system.print();
        }
        return system.totalEnergy();
    }
}
