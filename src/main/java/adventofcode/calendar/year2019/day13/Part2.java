package adventofcode.calendar.year2019.day13;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.run();
        return game.score;
    }
}
