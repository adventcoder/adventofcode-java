package adventofcode.calendar.year2018.day6;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Range;
import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return findMaxArea(parsePoints(input));
    }

    private List<Vector2D> parsePoints(String input) {
        List<Vector2D> points = new ArrayList<>();
        for (String line : input.split("\n")) {
            String[] parts = line.split(", ");
            points.add(new Vector2D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return points;
    }

    private Integer findMaxArea(List<Vector2D> as) {
        int maxArea = 0;
        for (int i = 0; i < as.size(); i++) {
            Integer area = findArea(as, i);
            if (area != null) {
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    private Integer findArea(List<Vector2D> as, int i) {
        if (isInfiniteArea(as, i)) {
            return null;
        }
        int area = 0;
        Range ys = new Range(as.get(i).y, as.get(i).y);
        while (isClosest(as, i, new Vector2D(as.get(i).x, ys.min - 1))) ys.min--;
        while (isClosest(as, i, new Vector2D(as.get(i).x, ys.max + 1))) ys.max++;
        for (int y : ys) {
            Range xs = new Range(as.get(i).x, as.get(i).x);
            while (isClosest(as, i, new Vector2D(xs.min - 1, y))) xs.min--;
            while (isClosest(as, i, new Vector2D(xs.max + 1, y))) xs.max++;
            area += xs.size();
        }
        return area;
    }

    private boolean isInfiniteArea(List<Vector2D> as, int i) {
        boolean isLeftMost = true;
        boolean isRightMost = true;
        boolean isUpMost = true;
        boolean isDownMost = true;
        for (int j = 0; j < as.size(); j++) {
            if (i == j) continue;
            Vector2D dir = as.get(j).sub(as.get(i));
            if (Math.abs(dir.y) <= -dir.x) isLeftMost = false;
            if (Math.abs(dir.y) <= dir.x) isRightMost = false;
            if (Math.abs(dir.x) <= -dir.y) isUpMost = false;
            if (Math.abs(dir.x) <= dir.y) isDownMost = false;
        }
        return isLeftMost || isRightMost || isUpMost || isDownMost;
    }

    private boolean isClosest(List<Vector2D> as, int i, Vector2D b) {
        int targetDistance = as.get(i).sub(b).abs();
        for (int j = 0; j < as.size(); j++) {
            if (i == j) continue;
            if (as.get(j).sub(b).abs() <= targetDistance) return false;
        }
        return true;
    }
}
