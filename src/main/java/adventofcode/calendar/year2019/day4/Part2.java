package adventofcode.calendar.year2019.day4;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Long> {
    @Override
    public Long solve(String input) {
        return PasswordStream.range(input)
                .withIncreasingDigits()
                .withDigitPairExactly()
                .count();
    }
}
