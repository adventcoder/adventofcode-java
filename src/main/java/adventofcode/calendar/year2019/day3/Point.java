package adventofcode.calendar.year2019.day3;

public class Point {
    public final int x;
    public final int y;
    public final int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int distanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }
}
