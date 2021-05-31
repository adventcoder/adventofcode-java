package adventofcode.year2019.day1;

import adventofcode.Session;
import adventofcode.Solver;

import java.io.IOException;

public class Part1 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.fromProperties();
        Part1 part1 = new Part1();
        part1.solveAndPrint("Answer:", session.getInput(2019, 1));
    }

    @Override
    public Integer solve(String input) {
        int totalFuel = 0;
        for (String line : input.split("\n")) {
            int mass = Integer.parseInt(line);
            totalFuel += mass / 3 - 2;
        }
        return totalFuel;
    }
}
