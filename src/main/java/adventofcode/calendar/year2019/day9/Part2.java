package adventofcode.calendar.year2019.day9;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;
import java.math.BigInteger;

public class Part2 extends Solver<BigInteger> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 9, new Part2());
    }

    @Override
    public BigInteger solve(String input) {
        IntComputer computer = new IntComputer(input);
        computer.nextInput(BigInteger.TWO);
        return computer.nextOutput();
    }
}
