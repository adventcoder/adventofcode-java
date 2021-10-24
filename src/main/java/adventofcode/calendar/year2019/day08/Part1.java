package adventofcode.calendar.year2019.day08;

import adventofcode.framework.AbstractPart;

import java.util.Arrays;

import static adventofcode.utils.Iterables.argMin;

public class Part1 extends AbstractPart<Integer> {
    private static final int width = 25;
    private static final int height = 6;

    @Override
    public Integer solve(String input) {
        int depth = input.length() / (width * height);
        int[][] counts = new int[depth][3];
        for (int z = 0; z < depth; z++) {
            int offset = z * width * height;
            for (int i = 0; i < width * height; i++) {
                int code = input.charAt(offset + i) - '0';
                counts[z][code]++;
            }
        }
        int[] minLayer = argMin((layer) -> layer[0], Arrays.asList(counts));
        return minLayer[1] * minLayer[2];
    }
}
