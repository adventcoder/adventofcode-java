package adventofcode.calendar.year2019.day11;

import adventofcode.calendar.year2019.common.IntComputer;

import java.math.BigInteger;

public class Robot extends IntComputer {
    private final Hull hull;
    private int x = 0;
    private int y = 0;
    private int dx = 0;
    private int dy = -1;
    private boolean firstOutput = true;

    public Robot(String program, Hull hull) {
        super(program);
        this.hull = hull;
    }

    @Override
    protected BigInteger get() {
        return hull.getColor(x, y);
    }

    @Override
    protected void put(BigInteger output) {
        if (firstOutput) {
            hull.putColor(x, y, output);
            firstOutput = false;
        } else {
            if (output.equals(BigInteger.ZERO)) {
                int temp = dx;
                dx = dy;
                dy = -temp;
            } else if (output.equals(BigInteger.ONE)) {
                int temp = dx;
                dx = -dy;
                dy = temp;
            }
            x += dx;
            y += dy;
            firstOutput = true;
        }
    }
}
