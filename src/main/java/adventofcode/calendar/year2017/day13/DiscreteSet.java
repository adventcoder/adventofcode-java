package adventofcode.calendar.year2017.day13;

import adventofcode.utils.IntMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscreteSet {
    public final List<Integer> starts;
    public final int step;

    private DiscreteSet(List<Integer> starts, int step) {
        this.starts = starts;
        this.step = step;
    }

    public DiscreteSet(int start, int step) {
        this(Collections.singletonList(Math.floorMod(start, step)), step);
    }

    public int get(int i) {
        return starts.get(i % starts.size()) + step * (i / starts.size());
    }

    public DiscreteSet delete(int otherStart, int otherStep) {
        int newStep = IntMath.lcm(step, otherStep);
        List<Integer> newStarts = new ArrayList<>();
        for (int i = 0; i < newStep / step; i++) {
            for (int start : starts) {
                int newStart = start + i * step;
                if (Math.floorMod(newStart - otherStart, otherStep) != 0) {
                    newStarts.add(newStart);
                }
            }
        }
        return new DiscreteSet(newStarts, newStep);
    }
}
