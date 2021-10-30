package adventofcode.calendar.year2018.day16;

import adventofcode.utils.IntArray;

import java.util.Arrays;

public class Sample {
    public final int[] before;
    public final int[] instr;
    public final int[] after;

    public Sample(String input) {
        String[] lines = input.split("\n");
        before = IntArray.parse(lines[0], "Before: [", ",\\s*", "]");
        instr = IntArray.parse(lines[1], "\\s+");
        after = IntArray.parse(lines[2], "After:  [", ",\\s*", "]");
    }

    public boolean test(Operator op) {
        int[] reg = before.clone();
        op.apply(instr, reg);
        return Arrays.equals(reg, after);
    }
}
