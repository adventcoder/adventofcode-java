package adventofcode.calendar.year2018.day1;

import adventofcode.framework.AbstractPart;

import java.util.*;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] lines = input.split("\n");
        int[] freqs = new int[lines.length];
        freqs[0] = Integer.parseInt(lines[0]);
        for (int i = 1; i < lines.length; i++) {
            freqs[i] = freqs[i - 1] + Integer.parseInt(lines[i]);
        }
        int step = freqs[freqs.length - 1];

        // group by residue
        int[] head = new int[step];
        Arrays.fill(head, -1);
        int[] next = new int[freqs.length];
        for (int i = 0; i < freqs.length; i++) {
            int r = Math.floorMod(freqs[i], step);
            next[i] = head[r];
            head[r] = i;
        }

        int minLoops = Integer.MAX_VALUE;
        Integer firstRepeated = null;
        for (int i = 0; i < freqs.length; i++) {
            int r = Math.floorMod(freqs[i], step);
            for (int j = head[r]; j != -1; j = next[j]) {
                if (i == j || freqs[i] > freqs[j]) continue;
                int loops = (freqs[j] - freqs[i]) / step;
                if (loops < minLoops) {
                    minLoops = loops;
                    firstRepeated = freqs[j];
                }
            }
        }

        return firstRepeated;
    }
}
