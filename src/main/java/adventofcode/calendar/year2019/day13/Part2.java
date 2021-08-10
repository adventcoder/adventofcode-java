package adventofcode.calendar.year2019.day13;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;
import java.math.BigInteger;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 13, new Part2());
    }

    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.set(0, BigInteger.TWO);
        game.run();
        return game.score;
    }
}
