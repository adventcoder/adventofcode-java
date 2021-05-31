package adventofcode.year2019.day3;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x ^ y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
        return false;
    }

    public int distanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }
}
