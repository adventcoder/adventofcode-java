package adventofcode.calendar.year2019.day2;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;
import adventofcode.calendar.year2019.common.Intcode;

import java.io.IOException;
import java.math.BigInteger;

public class Part1 extends Solver<BigInteger> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 2, new Part1());
    }

    @Override
    public BigInteger solve(String input) {
        Intcode intcode = new Intcode(input);
        intcode.set(1, BigInteger.valueOf(12));
        intcode.set(2, BigInteger.valueOf(2));
        intcode.resume();
        return  intcode.get(0);
    }
}
