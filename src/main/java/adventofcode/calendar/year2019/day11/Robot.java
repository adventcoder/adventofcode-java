package adventofcode.calendar.year2019.day11;

import adventofcode.calendar.year2019.Intcode;

import java.math.BigInteger;

public class Robot extends Intcode {
    private final Hull hull;
    private int x = 0;
    private int y = 0;
    private int dx = 0;
    private int dy = -1;
    private int count = 0;

    public Robot(String program, Hull hull) {
        super(program);
        this.hull = hull;
    }

    @Override
    protected BigInteger read() {
        return hull.getColor(x, y);
    }

    @Override
    protected void write(BigInteger output) {
        if (count == 0) {
            hull.putColor(x, y, output);
        } else {
            int oldDx = dx;
            if (output.equals(BigInteger.ZERO)) {
                dx = dy;
                dy = -oldDx;
            } else if (output.equals(BigInteger.ONE)) {
                dx = -dy;
                dy = oldDx;
            }
            x += dx;
            y += dy;
        }
        count = (count + 1) % 2;
    }
}
