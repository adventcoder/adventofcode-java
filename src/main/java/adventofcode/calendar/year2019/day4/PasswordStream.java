package adventofcode.calendar.year2019.day4;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PasswordStream {
    private static final int[] powers = { 100000, 10000, 1000, 100, 10, 1 };
    private static final int firstPassword = 100000;
    private static final int lastPassword = 999999;

    private static int getDigit(int password, int i) {
        return password / powers[i] % 10;
    }

    public static PasswordStream range(String input) {
        String[] range = input.split("-");
        int first = Math.max(Integer.parseInt(range[0]), firstPassword);
        int last = Math.min(Integer.parseInt(range[1]), lastPassword);
        return new PasswordStream(IntStream.rangeClosed(first, last));
    }

    private final IntStream stream;

    private PasswordStream(IntStream stream) {
        this.stream = stream;
    }

    public PasswordStream filter(IntPredicate predicate) {
        return new PasswordStream(stream.filter(predicate));
    }

    public PasswordStream withIncreasingDigits() {
        return filter((password) -> {
            for (int i = 0; i < powers.length - 1; i++) {
                if (getDigit(password, i) > getDigit(password, i + 1)) {
                    return false;
                }
            }
            return true;
        });
    }

    public PasswordStream withDigitPair() {
        return filter((password) -> {
            for (int i = 0; i < powers.length - 1; i++) {
                if (getDigit(password, i) == getDigit(password, i + 1)) {
                    return true;
                }
            }
            return false;
        });
    }

    public PasswordStream withDigitPairExactly() {
        return filter((password) -> {
            for (int i = 0; i < powers.length - 1; i++) {
                if (getDigit(password, i) == getDigit(password, i + 1)
                        && (i == 0 || getDigit(password, i - 1) != getDigit(password, i))
                        && (i == powers.length - 2 || getDigit(password, i + 2) != getDigit(password, i))) {
                    return true;
                }
            }
            return false;
        });
    }

    public long count() {
        return stream.count();
    }
}
