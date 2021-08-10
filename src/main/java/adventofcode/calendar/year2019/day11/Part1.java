package adventofcode.calendar.year2019.day11;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;

public class Part1 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 11, new Part1());
    }

    @Override
    public Integer solve(String input) {
        Hull hull = new Hull();
        Robot robot = new Robot(input, hull);
        robot.run();
        System.out.println(hull.toString());
        return hull.size();
    }
}
