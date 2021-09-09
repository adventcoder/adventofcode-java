package adventofcode.utils;

public class Vector2D implements Cloneable {
    public final int x;
    public final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Short.toUnsignedInt((short) x) << 16 | Short.toUnsignedInt((short) y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D) {
            Vector2D other = (Vector2D) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

    @Override
    protected Vector2D clone() {
        try {
            return (Vector2D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public Vector2D perpLeft() {
        return new Vector2D(y, -x);
    }

    public Vector2D perpRight() {
        return new Vector2D(-y, x);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }
}
