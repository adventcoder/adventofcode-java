package adventofcode.calendar.year2019.day16;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        byte[] digits = Digits.fromString(input.trim());
        int offset = Digits.toInteger(digits, 7);
        // From the half way point on (rounding up), all coefficients will be one.
        if (offset < (digits.length * 10000 + 1) / 2) {
            return null;
        }
        byte[] endDigits = Digits.subSequence(digits, 10000, offset);
        for (int phase = 1; phase <= 100; phase++) {
            fastTransform(endDigits);
        }
        return Digits.toInteger(endDigits, 8);
    }

    private void fastTransform(byte[] digits) {
        for (int i = digits.length - 2; i >= 0; i--) {
            int newDigit = digits[i] + digits[i + 1];
            digits[i] = (byte) (newDigit % 10);
        }
    }
}
