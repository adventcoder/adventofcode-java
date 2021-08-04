package adventofcode.calendar.year2019.day2;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;
import java.math.BigInteger;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 2, new Part2());
    }

    @Override
    public Integer solve(String input) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                IntComputer comp = new IntComputer(input);
                comp.set(1, BigInteger.valueOf(noun));
                comp.set(2, BigInteger.valueOf(verb));
                comp.run();
                if (comp.get(0).intValue() == 19690720) {
                    return noun * 100 + verb;
                }
            }
        }
        return null;
    }
}
