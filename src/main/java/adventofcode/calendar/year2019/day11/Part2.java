package adventofcode.calendar.year2019.day11;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<Hull> {
    @Override
    public Hull solve(String input) {
        Hull hull = new Hull();
        hull.putColor(0, 0, BigInteger.ONE);
        Robot robot = new Robot(input, hull);
        robot.run();
        return hull;
    }
}
