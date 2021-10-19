package adventofcode.calendar.year2019.day3;

public class Segment {
    public final int min;
    public final int max;
    private final int minDistance;
    private final int maxDistance;

    public Segment(int min, int max, int minDistance, int maxDistance) {
        this.min = min;
        this.max = max;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    public int distanceAt(int n) {
        return (minDistance * (max - n) + maxDistance * (n - min)) / (max - min);
    }
}
