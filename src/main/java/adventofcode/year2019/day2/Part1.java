package adventofcode.year2019.day2;

import adventofcode.Session;
import adventofcode.Solver;
import adventofcode.year2019.Intcode;

import java.io.IOException;
import java.math.BigInteger;

public class Part1 extends Solver<BigInteger> {
    public static void main(String[] args) throws IOException {
        Session session = Session.fromProperties();
        new Part1().solveAndPrint("Answer:", session.getInput(2019, 2));
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
