package adventofcode.calendar.year2019.day15;

public enum Dir {
    NORTH, SOUTH, WEST, EAST;

    public int intValue() {
        return ordinal() + 1;
    }

    public Dir opposite() {
        return Dir.values()[ordinal() ^ 1];
    }
}
