package adventofcode.utils;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
    public final int start;
    public final int end;

    public Range(int end) {
        this.start = 0;
        this.end = end;
    }

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int size() {
        return end - start;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int n = start;

            @Override
            public boolean hasNext() {
                return n < end;
            }

            @Override
            public Integer next() {
                return n++;
            }
        };
    }
}
