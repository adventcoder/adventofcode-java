package adventofcode.calendar.year2018.day3;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int[][] counts = new int[1000][1000];
        for (String line : input.split("\n")) {
            Claim claim = new Claim(line);
            for (int y = 0; y < claim.height; y++) {
                for (int x = 0; x < claim.width; x++) {
                    counts[y + claim.y][x + claim.x]++;
                }
            }
        }
        int totalCount = 0;
        for (int y = 0; y < counts.length; y++) {
            for (int x = 0; x < counts[y].length; x++) {
                if (counts[y][x] > 1) {
                    totalCount++;
                }
            }
        }
        return totalCount;
    }
}
