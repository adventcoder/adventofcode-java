package adventofcode.calendar.year2017.day10;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class KnotHash {
    private static final byte[] suffix = { 17, 31, 73, 47, 23 };

    public static KnotHash standard(String input) {
        KnotHash hash = new KnotHash(getLengths(input));
        for (int i = 0; i < 64; i++) {
            hash.round();
        }
        return hash;
    }

    private static int[] getLengths(String input) {
        int[] lengths = new int[input.length() + suffix.length];
        int i = 0;
        for (byte b : input.getBytes(StandardCharsets.US_ASCII)) {
            lengths[i++] = b;
        }
        for (byte b : suffix) {
            lengths[i++] = b;
        }
        return lengths;
    }

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

    public String toHexString() {
        StringBuilder builder = new StringBuilder(32);
        for (int i = 0; i < 16; i++) {
            builder.append(Integer.toHexString(denseByte(i)));
        }
        return builder.toString();
    }

    public byte[] toBitArray() {
        byte[] bits = new byte[128];
        for (int i = 0; i < 16; i++) {
            int b = denseByte(i);
            for (int j = 0; j < 8; j++) {
                bits[i * 8 + j] = (byte) ((b >>> (7 - j)) & 0x1);
            }
        }
        return bits;
    }

    public BigInteger toBigInteger() {
        byte[] bytes = new byte[17];
        for (int i = 1; i < bytes.length; i++) {
            bytes[i] = (byte) denseByte(i - 1);
        }
        return new BigInteger(bytes);
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
