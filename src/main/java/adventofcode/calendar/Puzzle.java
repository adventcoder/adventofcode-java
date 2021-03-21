package adventofcode.calendar;

import adventofcode.calendar.year2019.solvers.Day1Solver;

public class Puzzle {
    private final int year;
    private final int day;
    private final String title;
    private final String input;

    public Puzzle(int year, int day, String title, String input) {
        this.year = year;
        this.day = day;
        this.title = title;
        this.input = input;
    }

    public Solver solver() {
        if (day == 1) {
            return new Day1Solver(input);
        }
        return null;
    }

    public void solve() {
        Solver solver = solver();
        System.out.println(solver.part1());
        System.out.println(solver.part2());
    }
}
