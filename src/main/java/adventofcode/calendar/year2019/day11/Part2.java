package adventofcode.calendar.year2019.day11;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;
import java.math.BigInteger;

public class Part2 extends Solver<Hull> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 11, new Part2());
    }

    @Override
    public Hull solve(String input) {
        Hull hull = new Hull();
        hull.putColor(0, 0, BigInteger.ONE);
        Robot robot = new Robot(input, hull);
        robot.run();
        return hull;
    }
}
