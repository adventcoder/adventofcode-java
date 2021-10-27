package adventofcode.calendar.year2019.day3;

import java.util.NavigableMap;
import java.util.TreeMap;

public class SegmentList {
    private final NavigableMap<Integer, Segment> segments = new TreeMap<>();

    public void add(Segment segment) {
        segments.put(segment.min, segment);
    }

    public boolean contains(int n) {
        Integer min = segments.floorKey(n);
        return min != null && n <= segments.get(min).max;
    }

    public int distanceAt(int n) {
        return segments.get(segments.floorKey(n)).distanceAt(n);
    }
}
