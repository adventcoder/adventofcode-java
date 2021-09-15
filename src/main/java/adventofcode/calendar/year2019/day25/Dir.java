package adventofcode.calendar.year2019.day25;

public enum Dir {
    NORTH, SOUTH, WEST, EAST;

    public Dir opposite() {
        return values()[ordinal() ^ 1];
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Dir parseDir(String dir) {
        return valueOf(dir.toUpperCase());
    }
}
