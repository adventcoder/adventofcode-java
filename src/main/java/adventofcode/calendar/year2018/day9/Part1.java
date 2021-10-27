package adventofcode.calendar.year2018.day9;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Long> {
    @Override
    public Long solve(String input) {
        Game game = new Game(input);
        return game.highScore();
    }
}
