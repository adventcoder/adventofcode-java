package adventofcode.calendar.year2017.day2;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        for (String line : input.split("\n")) {
            int[] row = IntArray.parse(line, "\\s+");
            for (int i = 0; i < row.length; i++) {
                for (int j = i + 1; j < row.length; j++) {
                    int a = Math.min(row[i], row[j]);
                    int b = Math.max(row[i], row[j]);
                    if (b % a == 0) {
                        sum += b / a;
                    }
                }
            }
        }
        return sum;
    }
}
