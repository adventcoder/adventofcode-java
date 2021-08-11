package adventofcode.calendar.year2019.day13;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.set(0, BigInteger.TWO);
        game.run();
        return game.score;
    }
}
