package adventofcode.calendar.year2019.day1;

import adventofcode.calendar.Solver;
import adventofcode.utils.StringIO;

import java.io.IOException;

public class Day1 {
    public static void main(String[] args) throws IOException {
        Solver solver = new Day1Solver(getInput());
        System.out.println("Part 1: " + solver.part1());
        System.out.println("Part 2: " + solver.part2());
    }

    public static String getInput() throws IOException {
        return StringIO.read(Day1.class.getResource("Input.txt"));
    }
}
