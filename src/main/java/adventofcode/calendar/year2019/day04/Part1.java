package adventofcode.calendar.year2019.day04;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Long> {
    @Override
    public Long solve(String input) {
        return PasswordStream.range(input)
                .withIncreasingDigits()
                .withDigitPair()
                .count();
    }
}
