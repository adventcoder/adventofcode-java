package adventofcode.calendar.year2019.day13;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntMath;

import java.math.BigInteger;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Game game = new Game(input);
        game.set(0, BigInteger.TWO);
        game.run();
        return game.score;
    }

    private static class Game extends AbstractGame {
        public Game(String input) {
            super(input);
        }

        public int score;
        private int paddleX;
        private int ballX;

        @Override
        public BigInteger get() {
            return BigInteger.valueOf(IntMath.signum(ballX - paddleX));
        }

        @Override
        public void putTile(int tileX, int tileY, int tileId) {
            if (tileX == -1 && tileY == 0) {
                score = tileId;
            } else if (tileId == 3) {
                paddleX = tileX;
            } else if (tileId == 4) {
                ballX = tileX;
            }
        }
    }
}
