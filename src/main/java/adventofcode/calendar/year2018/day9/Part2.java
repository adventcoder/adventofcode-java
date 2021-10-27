package adventofcode.calendar.year2018.day9;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Long> {
    @Override
    public Long solve(String input) {
        Game game = new Game(input);
        game.last *= 100;
        return game.highScore();
    }
}
