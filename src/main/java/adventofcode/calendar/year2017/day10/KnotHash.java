package adventofcode.calendar.year2017.day10;

public class KnotHash {
    private static final String salt = new String(new char[] { 17, 31, 73, 47, 23 });
    private static final String hexChars = "0123456789abcdef";

    private final int[] lengths;
    private final byte[] hash = new byte[256];
    private int start;
    private int step;

    public KnotHash(int[] lengths) {
        this.lengths = lengths;
        for (int i = 0; i < hash.length; i++) {
            hash[i] = (byte) i;
        }
    }

    public KnotHash(String input) {
        this(getLengths(input + salt));
    }

    private static int[] getLengths(String input) {
        int[] lengths = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 0x80) throw new IllegalArgumentException();
            lengths[i] = c;
        }
        return lengths;
    }

    public int sparseByte(int i) {
        return Byte.toUnsignedInt(hash[i]);
    }

    public int denseByte(int i) {
        int b = 0;
        for (int j = 0; j < 16; j++) {
            b ^= sparseByte(i * 16 + j);
        }
        return b;
    }

    @Override
    public String toString() {
        char[] chars = new char[32];
        for (int i = 0; i < 16; i++) {
            int b = denseByte(i);
            chars[2 * i] = hexChars.charAt(b >>> 4);
            chars[2 * i + 1] = hexChars.charAt(b & 0xF);
        }
        return new String(chars);
    }

    public void round() {
        for (int length : lengths) {
            reverse(start, length);
            start = (start + length + step) & 0xFF;
            step++;
        }
    }

    private void reverse(int i, int length) {
        while (length > 1) {
            swap(i, (i + length - 1) & 0xFF);
            i = (i + 1) & 0xFF;
            length -= 2;
        }
    }

    private void swap(int i, int j) {
        byte tmp = hash[i];
        hash[i] = hash[j];
        hash[j] = tmp;
    }
}
