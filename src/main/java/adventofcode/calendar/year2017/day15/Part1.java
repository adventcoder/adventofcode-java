package adventofcode.calendar.year2017.day15;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    private static final long modulus = 2147483647;
    private static final long aFactor = 16807;
    private static final long bFactor = 48271;

    @Override
    public Integer solve(String input) {
        // Smarter way of doing this?
        String[] lines = input.split("\n");
        long a = Long.parseLong(lines[0].split("\\s+")[4]);
        long b = Long.parseLong(lines[1].split("\\s+")[4]);
        int count = 0;
        for (int i = 0; i < 40000000; i++) {
            a = a * aFactor % modulus;
            b = b * bFactor % modulus;
            if ((a & 0xFFFF) == (b & 0xFFFF)) {
                count++;
            }
        }
        return count;
    }
}
