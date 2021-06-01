package adventofcode.year2019.day4;

import adventofcode.Session;
import adventofcode.Solver;

import java.io.IOException;

public class Part1 extends Solver<Long> {
    public static void main(String[] args) throws IOException {
        Session session = Session.fromProperties();
        new Part1().solveAndPrint("Answer:", session.getInput(2019, 4));
    }

    @Override
    public Long solve(String input) {
        return Password.parseRange(input)
                .filter(Password::allIncreasingDigits)
                .filter(Password::anyDigitRuns)
                .count();
    }
}
