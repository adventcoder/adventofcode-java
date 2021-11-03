package adventofcode.calendar.year2017.day1;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        int halfLength = input.length() / 2;
        for (int i = 0; i < halfLength; i++) {
            char c = input.charAt(i);
            if (c == input.charAt(i + halfLength)) {
                sum += c - '0';
            }
        }
        return sum * 2;
    }
}
