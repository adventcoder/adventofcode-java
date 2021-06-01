package adventofcode.year2019.day4;

import java.util.stream.IntStream;

public class Password {
    private static final int[] powers = { 100000, 10000, 1000, 100, 10, 1 };

    public static int getDigit(int password, int i) {
        return password / powers[i] % 10;
    }

    public static IntStream parseRange(String input) {
        String[] range = input.split("-");
        int first = Integer.parseInt(range[0]);
        int last = Integer.parseInt(range[1]);
        return IntStream.rangeClosed(first, last);
    }

    public static boolean allIncreasingDigits(int password) {
        for (int i = 0; i < powers.length - 1; i++) {
            if (getDigit(password, i) > getDigit(password, i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean anyDigitRuns(int password) {
        for (int i = 0; i < powers.length - 1; i++) {
            if (getDigit(password, i) == getDigit(password, i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyDigitPairs(int password) {
        int i = 0;
        while (i < powers.length - 1) {
            int digit = getDigit(password, i);
            int count = 1;
            while (i + count < powers.length && digit == getDigit(password, i + count)) {
                count++;
            }
            if (count == 2) {
                return true;
            }
            i += count;
        }
        return false;
    }
}
