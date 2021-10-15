package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.Intcode;
import adventofcode.utils.IntMath;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Game extends Intcode {
    public int score;
    public int paddleX;
    public int ballX;
    private List<BigInteger> outputs = new ArrayList<>();

    public Game(String program) {
        super(program);
        set(0, BigInteger.TWO);
    }

    @Override
    protected BigInteger read() {
        return BigInteger.valueOf(IntMath.sgn(ballX - paddleX));
    }

    @Override
    protected void write(BigInteger output) {
        outputs.add(output);
        if (outputs.size() == 3) {
            write(outputs.get(0).intValueExact(), outputs.get(1).intValueExact(), outputs.get(2).intValueExact());
            outputs.clear();
        }
    }

    private void write(int x, int y, int tileId) {
        if (x == -1 && y == 0) {
            score = tileId;
        } else if (tileId == 3) {
            paddleX = x;
        } else if (tileId == 4) {
            ballX = x;
        }
    }
}
