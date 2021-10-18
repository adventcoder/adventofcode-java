package adventofcode.calendar.year2019.day16;

public class Digits {
    public static byte[] fromString(String str) {
        byte[] digits = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            digits[i] = (byte) (str.charAt(i) - '0');
        }
        return digits;
    }

    public static String toString(byte[] digits) {
        StringBuilder builder = new StringBuilder(digits.length);
        for (int i = 0; i < digits.length; i++) {
            builder.append((char) (digits[i] + '0'));
        }
        return builder.toString();
    }

    public static int toInteger(byte[] digits, int length) {
        int n = 0;
        for (int i = 0; i < length; i++) {
            n = n * 10 + digits[i];
        }
        return n;
    }

    public static byte[] subSequence(byte[] digits, int n, int offset) {
        byte[] subDigits = new byte[n * digits.length - offset];
        for (int i = 0; i < subDigits.length; i++) {
            subDigits[i] = digits[(offset + i) % digits.length];
        }
        return subDigits;
    }
}
