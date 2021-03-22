package adventofcode.calendar.year2019.things;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Wire {
    private Point endPoint = new Point(0, 0, 0);
    private final List<Segment> segments = new ArrayList<>();

    public static Wire parse(String line) {
        Wire wire = new Wire();
        for (String token : line.split(",")) {
            char direction = token.charAt(0);
            int length = Integer.parseInt(token.substring(1));
            if (direction == 'U') {
                wire.addUpSegment(length);
            } else if (direction == 'D') {
                wire.addDownSegment(length);
            } else if (direction == 'L') {
                wire.addLeftSegment(length);
            } else if (direction == 'R') {
                wire.addRightSegment(length);
            }
        }
        return wire;
    }

    public void addUpSegment(int length) {
        segments.add(new VSegment(endPoint.x, endPoint.y - length, endPoint.y - 1, endPoint.distance + length, endPoint.distance + 1));
        endPoint = new Point(endPoint.x, endPoint.y - length, endPoint.distance + length);
    }

    public void addDownSegment(int length) {
        segments.add(new VSegment(endPoint.x, endPoint.y + 1, endPoint.y + length, endPoint.distance + 1, endPoint.distance + length));
        endPoint = new Point(endPoint.x, endPoint.y + length, endPoint.distance + length);
    }

    public void addLeftSegment(int length) {
        segments.add(new HSegment(endPoint.x - length, endPoint.x - 1, endPoint.y, endPoint.distance + length, endPoint.distance + 1));
        endPoint = new Point(endPoint.x - length, endPoint.y, endPoint.distance + length);
    }

    public void addRightSegment(int length) {
        segments.add(new HSegment(endPoint.x + 1, endPoint.x + length, endPoint.y, endPoint.distance + 1, endPoint.distance + length));
        endPoint = new Point(endPoint.x + length, endPoint.y, endPoint.distance + length);
    }

    public List<Point> intersect(Wire that) {
        List<Point> points = new ArrayList<>();
        for (Segment a : this.segments) {
            for (Segment b : that.segments) {
                for (Point p : a.intersect(b)) {
                     points.add(p);
                }
            }
        }
        return points;
    }

    public interface Segment extends Iterable<Point> {
        Iterable<Point> intersect(Segment that);
        Iterable<Point> intersectWith(HSegment that);
        Iterable<Point> intersectWith(VSegment that);
    }

    public static class HSegment implements Segment {
        public final int xMin;
        public final int xMax;
        public final int xMinDistance;
        public final int xMaxDistance;
        public final int y;

        public HSegment(int xMin, int xMax, int y, int xMinDistance, int xMaxDistance) {
            this.xMin = xMin;
            this.xMax = xMax;
            this.y = y;
            this.xMinDistance = xMinDistance;
            this.xMaxDistance = xMaxDistance;
        }

        @Override
        public Iterator<Point> iterator() {
            return new Iterator<Point>() {
                private int x = xMin;

                @Override
                public boolean hasNext() {
                    return x <= xMax;
                }

                @Override
                public Point next() {
                    Point point = new Point(x, y, distanceAt(x));
                    x += 1;
                    return point;
                }
            };
        }

        public int distanceAt(int x) {
            int delta = (xMaxDistance - xMinDistance) / (xMax - xMin);
            return xMinDistance + delta * (x - xMin);
        }

        @Override
        public Iterable<Point> intersect(Segment that) {
            return that.intersectWith(this);
        }

        @Override
        public Iterable<Point> intersectWith(HSegment that) {
            if (this.y != that.y) {
                return Collections.emptyList();
            }
            int xMin = Math.max(this.xMin, that.xMin);
            int xMax = Math.min(this.xMax, that.xMax);
            if (xMin > xMax) {
                return Collections.emptyList();
            }
            int xMinDistance = this.distanceAt(xMin) + that.distanceAt(xMin);
            int xMaxDistance = this.distanceAt(xMax) + that.distanceAt(xMax);
            return new HSegment(xMin, xMax, y, xMinDistance, xMaxDistance);
        }

        @Override
        public Iterable<Point> intersectWith(VSegment that) {
            if (that.x < this.xMin || that.x > this.xMax) {
                return Collections.emptyList();
            }
            if (this.y < that.yMin || this.y > that.yMax) {
                return Collections.emptyList();
            }
            return Collections.singletonList(new Point(that.x, this.y, this.distanceAt(that.x) + that.distanceAt(this.y)));
        }
    }

    public static class VSegment implements Segment {
        public final int x;
        public final int yMin;
        public final int yMax;
        public final int yMinDistance;
        public final int yMaxDistance;

        public VSegment(int x, int yMin, int yMax, int yMinDistance, int yMaxDistance) {
            this.x = x;
            this.yMin = yMin;
            this.yMax = yMax;
            this.yMinDistance = yMinDistance;
            this.yMaxDistance = yMaxDistance;
        }

        @Override
        public Iterator<Point> iterator() {
            return new Iterator<Point>() {
                private int y = yMin;

                @Override
                public boolean hasNext() {
                    return y <= yMax;
                }

                @Override
                public Point next() {
                    Point point = new Point(x, y, distanceAt(y));
                    y += 1;
                    return point;
                }
            };
        }

        public int distanceAt(int y) {
            int delta = (yMaxDistance - yMinDistance) / (yMax - yMin);
            return yMinDistance + delta * (y - yMin);
        }

        @Override
        public Iterable<Point> intersect(Segment that) {
            return that.intersectWith(this);
        }

        @Override
        public Iterable<Point> intersectWith(HSegment that) {
            if (that.y < this.yMin || that.y > this.yMax) {
                return Collections.emptyList();
            }
            if (this.x < that.xMin || this.x > that.xMax) {
                return Collections.emptyList();
            }
            return Collections.singletonList(new Point(this.x, that.y, that.distanceAt(this.x) + this.distanceAt(that.y)));
        }

        @Override
        public Iterable<Point> intersectWith(VSegment that) {
            if (this.x != that.x) {
                return Collections.emptyList();
            }
            int yMin = Math.max(this.yMin, that.yMin);
            int yMax = Math.min(this.yMax, that.yMax);
            if (yMin > yMax) {
                return Collections.emptyList();
            }
            int yMinDistance = this.distanceAt(yMin) + that.distanceAt(yMin);
            int yMaxDistance = this.distanceAt(yMax) + that.distanceAt(yMax);
            return new VSegment(x, yMin, yMax, yMinDistance, yMaxDistance);
        }
    }

    public static class Point {
        public final int x;
        public final int y;
        public final int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
