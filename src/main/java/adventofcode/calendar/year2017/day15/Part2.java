package adventofcode.calendar.year2017.day15;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    private static final long modulus = 2147483647;
    private static final long aFactor = 16807;
    private static final long bFactor = 48271;

    @Override
    public Integer solve(String input) {
        String[] lines = input.split("\n");
        long a = Long.parseLong(lines[0].split("\\s+")[4]);
        long b = Long.parseLong(lines[1].split("\\s+")[4]);
        int count = 0;
        for (int i = 0; i < 5000000; i++) {
            do {
                a = a * aFactor % modulus;
            } while (a % 4 != 0);
            do {
                b = b * bFactor % modulus;
            } while (b % 8 != 0);
            if ((a & 0xFFFF) == (b & 0xFFFF)) {
                count++;
            }
        }
        return count;
    }
}
