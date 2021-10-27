package adventofcode.utils;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
    public static Range empty() {
        return new Range(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static Range all() {
        return new Range(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Range exclusive(int end) {
        return exclusive(0, end);
    }

    public static Range exclusive(int start, int end) {
        return new Range(start, end - 1);
    }

    public int min;
    public int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int size() {
        return max - min + 1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int n = min;

            @Override
            public boolean hasNext() {
                return n <= max;
            }

            @Override
            public Integer next() {
                return n++;
            }
        };
    }

    @Override
    public String toString() {
        return min + " .. " + max;
    }

    public boolean contains(int n) {
        return n >= min && n <= max;
    }

    public boolean containsAll(Range ns) {
        return ns.min >= min && ns.max <= max;
    }

    public boolean containsAny(Range ns) {
        return contains(ns.min) || contains(ns.max);
    }

    public void add(int n) {
        min = Math.min(min, n);
        max = Math.max(max, n);
    }

    public void addAll(Range ns) {
        min = Math.min(min, ns.min);
        max = Math.max(max, ns.max);
    }

    public void retain(int n) {
        min = Math.max(min, n);
        max = Math.min(max, n);
    }

    public void retainAll(Range ns) {
        min = Math.max(min, ns.min);
        max = Math.min(max, ns.max);
    }
}
