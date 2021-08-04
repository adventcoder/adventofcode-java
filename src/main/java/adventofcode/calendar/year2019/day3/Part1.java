package adventofcode.calendar.year2019.day3;

import adventofcode.framework.Session;
import adventofcode.framework.Solver;
import adventofcode.utils.Enumerable;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Part1 extends Solver<Integer> {
    public static void main(String[] args) throws IOException {
        Session session = Session.getInstance();
        session.printAnswer(2019, 3, new Part1());
    }

    @Override
    public Integer solve(String input) {
        Set<Point> crossPoints = null;
        for (String line : input.split("\n")) {
            if (crossPoints == null) {
                crossPoints = getPoints(line);
            } else {
                crossPoints.retainAll(getPoints(line));
            }
        }
        if (crossPoints == null) return null;
        return Enumerable.of(crossPoints).min(Point::distanceFromOrigin);
    }
    
    private Set<Point> getPoints(String line) {
        Set<Point> points = new HashSet<>();
        int x = 0;
        int y = 0;
        for (String token : line.split(",")) {
            int length = Integer.parseInt(token.substring(1));
            while (length > 0) {
                switch (token.charAt(0)) {
                case 'U': y--; break;
                case 'D': y++; break;
                case 'L': x--; break;
                case 'R': x++; break;
                }
                points.add(new Point(x, y));
                length--;
            }
        }
        return points;
    }
}