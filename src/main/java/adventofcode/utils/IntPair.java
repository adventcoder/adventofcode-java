package adventofcode.utils;

public class IntPair implements Cloneable {
    public int x;
    public int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (Short.toUnsignedInt((short) x) << 16) | Short.toUnsignedInt((short) y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntPair) {
            IntPair pair = (IntPair) obj;
            return x == pair.x && y == pair.y;
        }
        return false;
    }

    @Override
    public IntPair clone() {
        try {
            return (IntPair) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
