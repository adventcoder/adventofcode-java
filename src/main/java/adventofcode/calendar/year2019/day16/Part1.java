package adventofcode.calendar.year2019.day16;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    private static final byte[] pattern = { 0, 1, 0, -1 };
    private static final boolean verbose = false;

    @Override
    public Integer solve(String input) {
        byte[] signal = Digits.fromString(input.trim());
        if (verbose) {
            System.out.println("Input signal: " + Digits.toString(signal));
        }
        for (int phase = 1; phase <= 100; phase++) {
            transform(signal);
            if (verbose) {
                System.out.println("After " + phase + " " + (phase == 1 ? "phase" : "phases") + ": " + Digits.toString(signal));
            }
        }
        return Digits.toInteger(signal, 8);
    }

    private void transform(byte[] digits) {
        for (int i = 0; i < digits.length; i++) {
            int newDigit = 0;
            for (int j = i; j < digits.length; j++) {
                newDigit += coefficient(i, j) * digits[j];
            }
            digits[i] = (byte) (Math.abs(newDigit) % 10);
        }
    }

    private byte coefficient(int i, int j) {
        return pattern[(j + 1) / (i + 1) % pattern.length];
    }
}
