package adventofcode.calendar.year2018.day23;

public class Point {
    public static Point parse(String str) {
        String[] strs = str.substring(1, str.length() - 1).split(",");
        int x = Integer.parseInt(strs[0]);
        int y = Integer.parseInt(strs[1]);
        int z = Integer.parseInt(strs[2]);
        return new Point(x, y, z);
    }

    public final int x;
    public final int y;
    public final int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        return x + 1625 * (y + 1625 * z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return x == point.x && y == point.y && z == point.z;
        }
        return false;
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
    }

    public int distanceTo(Point point) {
        return Math.abs(point.x - x) + Math.abs(point.y - y) + Math.abs(point.z - z);
    }
}
