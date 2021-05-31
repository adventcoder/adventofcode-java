package adventofcode.year2019.day1;

import adventofcode.Session;
import adventofcode.Solver;

import java.io.IOException;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.fromProperties();
        Part2 part2 = new Part2();
        part2.solveAndPrint("Answer:", session.getInput(2019, 1));
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
