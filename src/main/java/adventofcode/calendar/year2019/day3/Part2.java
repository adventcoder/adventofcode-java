package adventofcode.calendar.year2019.day3;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;
import adventofcode.utils.Enumerable;

import java.io.IOException;
import java.util.*;

public class Part2 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 3, new Part2());
    }

    @Override
    public Integer solve(String input) {
        Map<Point, Integer> crossPoints = null;
        for (String line : input.split("\n")) {
            if (crossPoints == null) {
                crossPoints = getPoints(line);
            } else {
                mergePoints(crossPoints, getPoints(line));
            }
        }
        if (crossPoints == null) return null;
        return Enumerable.of(crossPoints.keySet()).min(crossPoints::get);
    }

    private Map<Point, Integer> getPoints(String line) {
        Map<Point, Integer> points = new HashMap<>();
        int x = 0;
        int y = 0;
        int distance = 0;
        for (String token : line.split(",")) {
            int length = Integer.parseInt(token.substring(1));
            while (length > 0) {
                switch (token.charAt(0)) {
                case 'U': y--; break;
                case 'D': y++; break;
                case 'L': x--; break;
                case 'R': x++; break;
                }
                distance++;
                points.put(new Point(x, y), distance);
                length--;
            }
        }
        return points;
    }

    private void mergePoints(Map<Point, Integer> points, Map<Point, Integer> newPoints) {
        Iterator<Point> iter = points.keySet().iterator();
        while (iter.hasNext()) {
            Point point = iter.next();
            if (newPoints.containsKey(point)) {
                points.put(point, points.get(point) + newPoints.get(point));
            } else {
                iter.remove();
            }
        }
    }
}
