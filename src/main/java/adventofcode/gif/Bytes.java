package adventofcode.gif;

public class Bytes {
    public static int getBit(byte[] bytes, int index, int bitIndex) {
        return (Byte.toUnsignedInt(bytes[index]) >>> bitIndex) & 1;
    }

    public static void setBit(byte[] bytes, int index, int bitIndex) {
        bytes[index] |= 1 << bitIndex;
    }

    public static int getBits(byte[] bytes, int index, int bitIndex, int bitLength) {
        int bitMask = (1 << bitLength) - 1;
        return (bytes[index] >>> bitIndex) & bitMask;
    }

    public static void setBits(byte[] bytes, int index, int bitIndex, int bitLength, int bits) {
        int bitMask = (1 << bitLength) - 1;
        bytes[index] &= ~(bitMask << bitIndex);
        bytes[index] |= (bits & bitMask) << bitIndex;
    }

    public static int getByte(byte[] bytes, int index) {
        return Byte.toUnsignedInt(bytes[index]);
    }

    public static void setByte(byte[] bytes, int index, int b) {
        bytes[index] = (byte) b;
    }

    public static int getWord(byte[] bytes, int index) {
        int low = getByte(bytes, index);
        int high = getByte(bytes, index + 1);
        return low | (high << 8);
    }

    public static void setWord(byte[] bytes, int index, int word) {
        setByte(bytes, index, word);
        setByte(bytes, index + 1, word >>> 8);
    }

    public static int getColor(byte[] bytes, int index) {
        int r = getByte(bytes, index);
        int g = getByte(bytes, index + 1);
        int b = getByte(bytes, index + 2);
        return (r << 16) | (g << 8) | b;
    }

    public static void setColor(byte[] bytes, int index, int color) {
        setByte(bytes, index, color >>> 16);
        setByte(bytes, index + 1, color >>> 8);
        setByte(bytes, index + 2, color);
    }
}
