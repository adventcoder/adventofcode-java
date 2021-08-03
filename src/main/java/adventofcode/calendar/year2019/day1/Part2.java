package adventofcode.calendar.year2019.day1;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 1, new Part2());
    }

    @Override
    public Integer solve(String input) {
        int totalFuel = 0;
        for (String line : input.split("\n")) {
            int mass = Integer.parseInt(line);
            while (mass > 8) {
                int fuel = mass / 3 - 2;
                totalFuel += fuel;
                mass = fuel;
            }
        }
        return totalFuel;
    }
}
