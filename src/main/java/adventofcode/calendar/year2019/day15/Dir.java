package adventofcode.calendar.year2019.day15;

import java.math.BigInteger;

public enum Dir {
    NORTH, SOUTH, WEST, EAST;

    public BigInteger value() {
        return BigInteger.valueOf(ordinal() + 1);
    }

    public Dir opposite() {
        return Dir.values()[ordinal() ^ 1];
    }
}
