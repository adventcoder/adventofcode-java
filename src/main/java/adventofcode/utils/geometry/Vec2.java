package adventofcode.utils.geometry;

public class Vec2 implements Cloneable {
    public static final Vec2 ZERO = new Vec2(0, 0);
    public static final Vec2 UP = new Vec2(0, -1);
    public static final Vec2 DOWN = new Vec2(0, 1);
    public static final Vec2 LEFT = new Vec2(-1, 0);
    public static final Vec2 RIGHT = new Vec2(1, 0);

    public int x;
    public int y;

    public Vec2() {
    }

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public void negate() {
        x = -x;
        y = -y;
    }

    public void rotateLeft() {
        int oldY = y;
        y = -x;
        x = oldY;
    }

    public void rotateRight() {
        int oldY = y;
        y = x;
        x = -oldY;
    }

    public void add(Vec2 vec) {
        x += vec.x;
        y += vec.y;
    }

    public void sub(Vec2 vec) {
        x -= vec.x;
        y -= vec.y;
    }

    public void mul(int scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void addMul(Vec2 vec, int scalar) {
        x += scalar * vec.x;
        y += scalar * vec.y;
    }

    public void subMul(Vec2 vec, int scalar) {
        x -= scalar * vec.x;
        y -= scalar * vec.y;
    }

    public void div(int scalar) {
        x /= scalar;
        y /= scalar;
    }

    public void mod(int scalar) {
        x %= scalar;
        y %= scalar;
    }

    public int dot(Vec2 vec) {
        return x * vec.x + y * vec.y;
    }

    public int cross(Vec2 vec) {
        return x * vec.y - y * vec.x;
    }

    @Override
    public Vec2 clone() {
        try {
            return (Vec2) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int hashCode() {
        return (Short.toUnsignedInt((short) x) << 16) | Short.toUnsignedInt((short) y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec2) {
            Vec2 vec = (Vec2) obj;
            return x == vec.x && y == vec.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}
