package adventofcode.calendar.year2019.day11;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Hull hull = new Hull();
        Robot robot = new Robot(input, hull);
        robot.run();
        System.out.println(hull.toString());
        return hull.size();
    }
}
