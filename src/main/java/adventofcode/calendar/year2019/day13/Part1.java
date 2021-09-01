package adventofcode.calendar.year2019.day13;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.run();
        return game.blockCount;
    }

    private static class Game extends AbstractGame {
        public Game(String input) {
            super(input);
        }

        public int blockCount = 0;

        @Override
        public void putTile(int tileX, int tileY, int tileId) {
            if (tileId == 2) blockCount++;
        }
    }
}
