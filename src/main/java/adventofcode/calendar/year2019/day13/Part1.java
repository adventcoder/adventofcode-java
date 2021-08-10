package adventofcode.calendar.year2019.day13;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;

import java.io.IOException;

public class Part1 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 13, new Part1());
    }

    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.run();
        return game.getTileCount(Game.BLOCK);
    }
}
