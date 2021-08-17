package adventofcode.calendar.year2019.day15;

import adventofcode.utils.search.Invertible;

import java.math.BigInteger;

public enum Dir implements Invertible<Dir> {
    NORTH, SOUTH, WEST, EAST;

    public BigInteger value() {
        return BigInteger.valueOf(ordinal() + 1);
    }

    @Override
    public Dir inverse() {
        return Dir.values()[ordinal() ^ 1];
    }
}
