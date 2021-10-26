package adventofcode.calendar.year2018.day02;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int count2 = 0;
        int count3 = 0;
        for (String boxId : input.split("\n")) {
            int[] counts = new int[26];
            for (int i = 0; i < boxId.length(); i++) {
                counts[boxId.charAt(i) - 'a']++;
            }
            int hasCount = 0;
            for (int count : counts) {
                hasCount |= 1 << count;
            }
            count2 += (hasCount >>> 2) & 1;
            count3 += (hasCount >>> 3) & 1;
        }
        return count2 * count3;
    }
}
