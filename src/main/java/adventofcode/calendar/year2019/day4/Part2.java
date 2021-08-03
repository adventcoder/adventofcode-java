package adventofcode.calendar.year2019.day4;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;

public class Part2 extends Solver<Long> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 4, new Part2());
    }

    @Override
    public Long solve(String input) {
        return PasswordStream.range(input)
                .withIncreasingDigits()
                .withDigitPairExactly()
                .count();
    }
}
