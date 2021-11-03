package adventofcode.calendar.year2017.day1;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                sum += input.charAt(i) - '0';
            }
        }
        if (input.charAt(input.length() - 1) == input.charAt(0)) {
            sum += input.charAt(0) - '0';
        }
        return sum;
    }
}
