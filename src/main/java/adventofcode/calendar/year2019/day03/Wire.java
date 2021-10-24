package adventofcode.calendar.year2019.day03;

import java.util.*;
import java.util.function.Consumer;

public class Wire {
    // We just loop over the horizontal segments so need to optimise for lookup.
    private final Map<Integer, List<Segment>> horizontals = new HashMap<>();
    private final NavigableMap<Integer, SegmentList> verticals = new TreeMap<>();

    public Wire(String str) {
        int x = 0;
        int y = 0;
        int distance = 0;
        for (String token : str.split(",")) {
            int length = Integer.parseInt(token.substring(1));
            if (token.startsWith("L")) {
                addHorizontal(new Segment(x - length, x - 1, distance + length, distance + 1), y);
                x -= length;
            } else if (token.startsWith("R")) {
                addHorizontal(new Segment(x + 1, x + length, distance + 1, distance + length), y);
                x += length;
            } else if (token.startsWith("U")) {
                addVertical(x, new Segment(y - length, y - 1, distance + length, distance + 1));
                y -= length;
            } else if (token.startsWith("D")) {
                addVertical(x, new Segment(y + 1, y + length, distance + 1, distance + length));
                y += length;
            } else {
                throw new IllegalArgumentException(str);
            }
            distance += length;
        }
    }

    private void addHorizontal(Segment xs, int y) {
        if (!horizontals.containsKey(y)) {
            horizontals.put(y, new ArrayList<>());
        }
        horizontals.get(y).add(xs);
    }

    private void addVertical(int x, Segment ys) {
        if (!verticals.containsKey(x)) {
            verticals.put(x, new SegmentList());
        }
        verticals.get(x).add(ys);
    }

    public List<Point> cross(Wire that) {
        List<Point> crossPoints = new ArrayList<>();
        this.crossHorizontals(that, crossPoints::add);
        that.crossHorizontals(this, crossPoints::add);
        return crossPoints;
    }

    private void crossHorizontals(Wire that, Consumer<Point> action) {
        for (int y : horizontals.keySet()) {
            for (Segment xs : horizontals.get(y)) {
                that.crossHorizontal(y, xs, action);
            }
        }
    }

    private void crossHorizontal(int y, Segment xs, Consumer<Point> action) {
        for (Integer x = verticals.floorKey(xs.min); x != null && x <= xs.max; x = verticals.higherKey(x)) {
            SegmentList ys = verticals.get(x);
            if (ys.contains(y)) {
                action.accept(new Point(x, y, xs.distanceAt(x) + ys.distanceAt(y)));
            }
        }
    }
}
