package adventofcode.calendar.year2018.day25;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        List<int[]> points = new ArrayList<>();
        for (String line : input.split("\n")) {
            points.add(IntArray.parse(line, ","));
        }
        DisjointSet constellations = new DisjointSet(points.size());
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (distance(points.get(i), points.get(j)) <= 3) {
                    constellations.merge(i, j);
                }
            }
        }
        return constellations.size();
    }

    private int distance(int[] a, int [] b) {
        int distance = 0;
        for (int i = 0; i < a.length; i++) {
            distance += Math.abs(b[i] - a[i]);
        }
        return distance;
    }
}
