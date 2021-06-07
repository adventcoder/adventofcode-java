package adventofcode.year2019.day2;

import adventofcode.Session;
import adventofcode.Solver;
import adventofcode.year2019.Intcode;

import java.io.IOException;
import java.math.BigInteger;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.fromProperties();
        new Part2().solveAndPrint("Answer:", session.getInput(2019, 2));
    }

    @Override
    public Integer solve(String input) {
        Intcode intcode = new Intcode(input);
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                intcode.set(1, BigInteger.valueOf(noun));
                intcode.set(2, BigInteger.valueOf(verb));
                intcode.resume();
                if (intcode.get(0).intValue() == 19690720) {
                    return noun * 100 + verb;
                }
                intcode.reset();
            }
        }
        return null;
    }
}
