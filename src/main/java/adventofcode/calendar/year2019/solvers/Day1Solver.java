package adventofcode.calendar.year2019.solvers;

import adventofcode.calendar.Solver;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1Solver implements Solver {
    private final int[] masses;

    public Day1Solver(String input) {
        this.masses = Stream.of(input.split("\n")).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    public Integer part1() {
        return IntStream.of(masses).map(this::fuelNeeded).sum();
    }

    @Override
    public Integer part2() {
        return IntStream.of(masses).map(this::fuelNeededRecursive).sum();
    }

    private int fuelNeeded(int mass) {
        return mass / 3 - 2;
    }

    private int fuelNeededRecursive(int mass) {
        int totalFuel = 0;
        int fuel = fuelNeeded(mass);
        while (fuel >= 0) {
            totalFuel += fuel;
            fuel = fuelNeeded(fuel);
        }
        return totalFuel;
    }
}
