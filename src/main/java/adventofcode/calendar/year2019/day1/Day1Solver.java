package adventofcode.calendar.year2019.day1;

import adventofcode.calendar.Solver;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1Solver implements Solver {
    public final int[] masses;

    public Day1Solver(String input) {
        masses = Stream.of(input.split("\n"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Override
    public Integer part1() {
        return IntStream.of(masses).map(this::fuelNeeded).sum();
    }

    @Override
    public Integer part2() {
        return IntStream.of(masses).map(this::fuelNeededRecursive).sum();
    }

    public int fuelNeeded(int mass) {
        return mass / 3 - 2;
    }

    public int fuelNeededRecursive(int mass) {
        int totalFuel = 0;
        int fuel = fuelNeeded(mass);
        while (fuel > 0) {
            totalFuel += fuel;
            fuel = fuelNeeded(fuel);
        }
        return totalFuel;
    }
}
