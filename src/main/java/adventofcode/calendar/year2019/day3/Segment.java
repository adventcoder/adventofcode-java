package adventofcode.calendar.year2019.day3;

import java.util.Iterator;

public class Segment implements Iterable<Point> {
    private final int xMin;
    private final int xMax;
    private final int y;
    private final int distanceAtXMin;
    private final int distanceAtXMax;
    private final boolean transpose;

    public Segment(int xMin, int xMax, int y, int distanceAtXMin, int distanceAtXMax, boolean transpose) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.y = y;
        this.distanceAtXMin = distanceAtXMin;
        this.distanceAtXMax = distanceAtXMax;
        this.transpose = transpose;
    }

    public static Segment parse(Point start, String token) {
        int length = Integer.parseInt(token.substring(1));
        if (token.startsWith("L")) {
            return new Segment(start.x - length, start.x - 1, start.y, start.distance + length, start.distance + 1, false);
        } else if (token.startsWith("R")) {
            return new Segment(start.x + 1, start.x + length, start.y, start.distance + 1, start.distance + length, false);
        } else if (token.startsWith("U")) {
            return new Segment(start.y - length, start.y - 1, start.x, start.distance + length, start.distance + 1, true);
        } else if (token.startsWith("D")) {
            return new Segment(start.y + 1, start.y + length, start.x, start.distance + 1, start.distance + length, true);
        } else {
            throw new IllegalArgumentException(token);
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int x = xMin;

            @Override
            public boolean hasNext() {
                return x <= xMax;
            }

            @Override
            public Point next() {
                return pointAt(x++);
            }
        };
    }

    public Point end() {
        return distanceAtXMin <= distanceAtXMax ? pointAt(xMax) : pointAt(xMin);
    }

    public int distanceAt(int x) {
        if (x == xMax) return distanceAtXMax;
        return (distanceAtXMin * (xMax - x) + distanceAtXMax * (x - xMin)) / (xMax - xMin);
    }

    public Point pointAt(int x) {
        if (transpose) {
            return new Point(y, x, distanceAt(x));
        } else {
            return new Point(x, y, distanceAt(x));
        }
    }

    public Segment cross(Segment other) {
        if (transpose == other.transpose) {
            if (y == other.y) {
                int newXMin = Math.max(xMin, other.xMin);
                int newXMax = Math.min(xMax, other.xMax);
                if (newXMin < newXMax) {
                    int newDistanceAtXMin = distanceAt(newXMin) + other.distanceAt(newXMin);
                    int newDistanceAtXMax = distanceAt(newXMax) + other.distanceAt(newXMax);
                    return new Segment(newXMin, newXMax, y, newDistanceAtXMin, newDistanceAtXMax, transpose);
                }
            }
        } else {
            if (y >= other.xMin && y <= other.xMax && other.y >= xMin && other.y <= xMax) {
                int distance = distanceAt(other.y) + other.distanceAt(y);
                return new Segment(other.y, other.y, y, distance, distance, transpose);
            }
        }
        return null;
    }
}
