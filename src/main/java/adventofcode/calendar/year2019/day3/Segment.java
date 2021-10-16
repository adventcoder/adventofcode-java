package adventofcode.calendar.year2019.day3;

import java.util.*;
import java.util.function.Consumer;

public abstract class Segment implements Iterable<Point> {
    public static void forEach(String wire, Consumer<Segment> action) {
        int x = 0;
        int y = 0;
        int distance = 0;
        for (String token : wire.split(",")) {
            int length = Integer.parseInt(token.substring(1));
            switch (token.charAt(0)) {
            case 'U':
                action.accept(new Vertical(x, y - length, y - 1, distance + length, distance + 1));
                y -= length;
                break;
            case 'D':
                action.accept(new Vertical(x, y + 1, y + length, distance + 1, distance + length));
                y += length;
                break;
            case 'L':
                action.accept(new Horizontal(x - length, x - 1, y, distance + length, distance + 1));
                x -= length;
                break;
            case 'R':
                action.accept(new Horizontal(x + 1, x + length, y, distance + 1, distance + length));
                x += length;
                break;
            default:
                throw new IllegalArgumentException("dir: " + token.charAt(0));
            }
            distance += length;
        }
    }

    public static List<Segment> cross(String[] wires) {
        List<Segment> crossings = new ArrayList<>();
        Segment.forEach(wires[0], crossings::add);
        for (int i = 1; i < wires.length; i++) {
            crossings = Segment.cross(crossings, wires[i]);
        }
        return crossings;
    }

    public static List<Segment> cross(List<Segment> segments, String wire) {
        List<Segment> crossing = new ArrayList<>();
        forEach(wire, (a) -> {
            segments.forEach((b) -> {
                Segment c = a.intersect(b);
                if (c != null) {
                    crossing.add(c);
                }
            });
        });
        return crossing;
    }

    public abstract Segment intersect(Segment other);

    protected abstract Segment intersectBy(Horizontal horz);
    protected abstract Segment intersectBy(Vertical vert);
    protected abstract Segment intersectBy(Unit unit);

    public static class Horizontal extends Segment {
        private final int xMin;
        private final int xMax;
        private final int y;
        private final int distanceAtXmin;
        private final int distanceAtXMax;

        public Horizontal(int xMin, int xMax, int y, int distanceAtXmin, int distanceAtXMax) {
            this.xMin = xMin;
            this.xMax = xMax;
            this.y = y;
            this.distanceAtXmin = distanceAtXmin;
            this.distanceAtXMax = distanceAtXMax;
        }

        public int distanceAt(int x) {
            return (distanceAtXmin * (xMax - x) + distanceAtXMax * (x - xMin)) / (xMax - xMin);
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
                    int distance = distanceAt(x);
                    return new Point(x++, y, distance);
                }
            };
        }

        @Override
        public Segment intersect(Segment other) {
            return other.intersectBy(this);
        }

        @Override
        protected Segment intersectBy(Horizontal horz) {
            if (y == horz.y) {
                int newXMin = Math.max(xMin, horz.xMin);
                int newXMax = Math.min(xMax, horz.xMin);
                return new Horizontal(newXMin, newXMax, y, distanceAt(newXMin) + horz.distanceAt(newXMin), distanceAt(newXMax) + horz.distanceAt(newXMax));
            }
            return null;
        }

        @Override
        protected Segment intersectBy(Vertical vert) {
            if (vert.x >= xMin && vert.x <= xMax && y >= vert.yMin && y <= vert.yMax) {
                return new Unit(vert.x, y, distanceAt(vert.x) + vert.distanceAt(y));
            }
            return null;
        }

        @Override
        protected Segment intersectBy(Unit unit) {
            if (unit.y == y && unit.x >= xMin && unit.x <= xMax) {
                return new Unit(unit.x, unit.y, unit.distance + distanceAt(unit.x));
            }
            return null;
        }
    }

    public static class Vertical extends Segment {
        private final int x;
        private final int yMin;
        private final int yMax;
        private final int distanceAtYMin;
        private final int distanceAtYMax;

        public Vertical(int x, int yMin, int yMax, int distanceAtYMin, int distanceAtYMax) {
            this.x = x;
            this.yMin = yMin;
            this.yMax = yMax;
            this.distanceAtYMin = distanceAtYMin;
            this.distanceAtYMax = distanceAtYMax;
        }

        public int distanceAt(int y) {
            return (distanceAtYMin * (yMax - y) + distanceAtYMax * (y - yMin)) / (yMax - yMin);
        }

        @Override
        public Iterator<Point> iterator() {
            return new Iterator<>() {
                private int y = yMin;

                @Override
                public boolean hasNext() {
                    return y <= yMax;
                }

                @Override
                public Point next() {
                    int distance = distanceAt(y);
                    return new Point(x, y++, distance);
                }
            };
        }

        @Override
        public Segment intersect(Segment other) {
            return other.intersectBy(this);
        }

        @Override
        protected Segment intersectBy(Vertical vert) {
            if (x == vert.x) {
                int newYMin = Math.max(yMin, vert.yMin);
                int newYMax = Math.min(yMax, vert.yMin);
                return new Vertical(x, newYMin, newYMax, distanceAt(newYMin) + vert.distanceAt(newYMin), distanceAt(newYMax) + vert.distanceAt(newYMax));
            }
            return null;
        }

        @Override
        protected Segment intersectBy(Horizontal horz) {
            if (horz.y >= yMin && horz.y <= yMax && x >= horz.xMin && x <= horz.xMax) {
                return new Unit(x, horz.y, distanceAt(horz.y) + horz.distanceAt(x));
            }
            return null;
        }

        @Override
        protected Segment intersectBy(Unit unit) {
            if (unit.x == x && unit.y >= yMin && unit.y <= yMax) {
                return new Unit(unit.x, unit.y, unit.distance + distanceAt(unit.y));
            }
            return null;
        }
    }

    public static class Unit extends Segment {
        private final int x;
        private final int y;
        private final int distance;

        public Unit(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public Iterator<Point> iterator() {
            return new Iterator<>() {
                private boolean hasNext = true;

                @Override
                public boolean hasNext() {
                    return hasNext;
                }

                @Override
                public Point next() {
                    if (!hasNext) throw new NoSuchElementException();
                    hasNext = false;
                    return new Point(x, y, distance);
                }
            };
        }

        @Override
        public Segment intersect(Segment other) {
            return other.intersectBy(this);
        }

        @Override
        protected Segment intersectBy(Horizontal horz) {
            return horz.intersectBy(this);
        }

        @Override
        protected Segment intersectBy(Vertical vert) {
            return vert.intersectBy(this);
        }

        @Override
        protected Segment intersectBy(Unit unit) {
            if (x == unit.x && y == unit.y) {
                return new Unit(x, y, distance + unit.distance);
            }
            return null;
        }
    }
}
