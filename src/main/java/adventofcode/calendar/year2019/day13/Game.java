package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.utils.IntMath;
import adventofcode.utils.IntPair;

import java.math.BigInteger;
import java.util.*;

public class Game extends IntComputer {
    public static final BigInteger EMPTY = BigInteger.valueOf(0);
    public static final BigInteger WALL = BigInteger.valueOf(1);
    public static final BigInteger BLOCK = BigInteger.valueOf(2);
    public static final BigInteger PADDLE = BigInteger.valueOf(3);
    public static final BigInteger BALL = BigInteger.valueOf(4);

    public int score;
    private final Map<BigInteger, Set<IntPair>> tilePositions = new HashMap<>();
    private final List<BigInteger> outputs = new ArrayList<>();

    public Game(String program) {
        super(program);
    }

    @Override
    protected BigInteger get() {
        IntPair paddlePos = tilePositions.get(PADDLE).iterator().next();
        IntPair ballPos = tilePositions.get(BALL).iterator().next();
        return BigInteger.valueOf(IntMath.signum(ballPos.x - paddlePos.x));
    }

    @Override
    protected void put(BigInteger output) {
        outputs.add(output);
        if (outputs.size() == 3) {
            putTile(new IntPair(outputs.get(0).intValue(), outputs.get(1).intValue()), outputs.get(2));
            outputs.clear();
        }
    }

    public int getTileCount(BigInteger tileId) {
        return tilePositions.get(tileId).size();
    }

    public BigInteger getTile(IntPair pos) {
        for (BigInteger tileId : tilePositions.keySet()) {
            if (tilePositions.get(tileId).contains(pos)) {
                return tileId;
            }
        }
        return null;
    }

    public void putTile(IntPair pos, BigInteger tileId) {
        if (pos.x == -1 && pos.y == 0) {
            score = tileId.intValue();
        } else {
            for (BigInteger key : tilePositions.keySet()) {
                tilePositions.get(key).remove(pos);
            }
            tilePositions.computeIfAbsent(tileId, (key) -> new HashSet<>()).add(pos);
        }
    }

    public void print() {
        IntPair max = new IntPair(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (BigInteger tileId : tilePositions.keySet()) {
            for (IntPair pos : tilePositions.get(tileId)) {
                max.x = Math.max(max.x, pos.x);
                max.y = Math.max(max.y, pos.y);
            }
        }
        System.out.println("Score: " + score);
        for (int y = 0; y <= max.y; y++) {
            for (int x = 0; x <= max.x; x++) {
                System.out.print(".#+-o".charAt(getTile(new IntPair(x, y)).intValue()));
            }
            System.out.println();
        }
    }
}
