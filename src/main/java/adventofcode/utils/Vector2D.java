package adventofcode.utils;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D orig) {
        x = orig.x;
        y = orig.y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || (obj instanceof Vector2D && equals((Vector2D) obj));
    }

    private boolean equals(Vector2D vec) {
        return x == vec.x && y == vec.y;
    }

    @Override
    public int hashCode() {
        return Short.toUnsignedInt((short) x) << 16 | Short.toUnsignedInt((short) y);
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public int abs() {
        return Math.abs(x) + Math.abs(y);
    }

    public int magnitudeSquared() {
        return x * x + y * y;
    }

    public void negate() {
        x = -x;
        y = -y;
    }

    public Vector2D add(Vector2D vec) {
        return new Vector2D(x + vec.x, y + vec.y);
    }

    public void addEq(Vector2D vec) {
        x += vec.x;
        y += vec.y;
    }

    public Vector2D sub(Vector2D vec) {
        return new Vector2D(x - vec.x, y - vec.y);
    }

    public void subEq(Vector2D vec) {
        x -= vec.x;
        y -= vec.y;
    }

    public void mulEq(int n) {
        x *= n;
        y *= n;
    }

    public void divEq(int n) {
        x /= n;
        y /= n;
    }

    public void modEq(int n) {
        x %= n;
        y %= n;
    }

    public int dot(Vector2D vec) {
        return x * vec.x + y * vec.y;
    }

    public int cross(Vector2D vec) {
        return x * vec.y - y * vec.x;
    }

    /**
     * Rotate 90 degrees clockwise (assuming that the x axis points right and the y axis points down).
     */
    public void rotateRight() {
        int oldX = x;
        x = -y;
        y = oldX;
    }

    /**
     * Rotate 90 degrees anti-clockwise (assuming that the x axis points right and the y axis points down).
     */
    public void rotateLeft() {
        int oldX = x;
        x = y;
        y = -oldX;
    }

    /**
     * Get angle of rotation from this vector clockwise to the target vector, 0 <= angle < 2 pi.
     */
    public double angleTo(Vector2D vec) {
        double angle = Math.atan2(cross(vec), dot(vec));
        if (angle < 0.0) angle += 2 * Math.PI;
        return angle;
    }
}
