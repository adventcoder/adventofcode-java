package adventofcode.calendar.year2018.day6;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Range;
import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.ToIntFunction;

import static adventofcode.utils.Iterables.sum;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return findArea(parsePoints(input), 10000);
    }

    private List<Vector2D> parsePoints(String input) {
        List<Vector2D> points = new ArrayList<>();
        for (String line : input.split("\n")) {
            String[] parts = line.split(", ");
            points.add(new Vector2D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return points;
    }

    private int findArea(List<Vector2D> as, int radius) {
        int area = 0;
        Vector2D origin = centroid(as);
        Range ys = new Range(origin.y, origin.y);
        while (totalDistance(as, new Vector2D(origin.x, ys.min - 1)) < radius) ys.min--;
        while (totalDistance(as, new Vector2D(origin.x, ys.max + 1)) < radius) ys.max++;
        for (int y : ys) {
            Range xs = new Range(origin.x, origin.x);
            while (totalDistance(as, new Vector2D(xs.min - 1, y)) < radius) xs.min--;
            while (totalDistance(as, new Vector2D(xs.max + 1, y)) < radius) xs.max++;
            area += xs.size();
        }
        return area;
    }

    private Vector2D centroid(List<Vector2D> points) {
        return new Vector2D(median(points, Vector2D::x), median(points, Vector2D::y));
    }

    private <T> int median(List<T> list, ToIntFunction<T> f) {
        list.sort(Comparator.comparingInt(f));
        int i = list.size() / 2;
        if (list.size() % 2 == 0) {
            return (f.applyAsInt(list.get(i - 1)) + f.applyAsInt(list.get(i))) / 2;
        } else {
            return f.applyAsInt(list.get(i));
        }
    }

    private int totalDistance(List<Vector2D> as, Vector2D b) {
        return sum((a) -> a.sub(b).abs(), as);
    }
}
