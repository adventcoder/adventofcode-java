package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntMath;

import java.math.BigInteger;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int score = 0;
        int paddleX = 0;
        int ballX = 0;
        IntComputer game = new IntComputer(input);
        game.set(0, BigInteger.TWO);
        while (!game.halting()) {
            if (game.outputting()) {
                int x = game.nextOutput().intValue();
                int y = game.nextOutput().intValue();
                int tileId = game.nextOutput().intValue();
                if (x == -1 && y == 0) {
                    score = tileId;
                } else if (tileId == 3) {
                    paddleX = x;
                } else if (tileId == 4) {
                    ballX = x;
                }
            } else if (game.inputting()) {
                game.acceptInput(BigInteger.valueOf(IntMath.sgn(ballX - paddleX)));
            } else {
                game.step();
            }
        }
        return score;
    }
}
