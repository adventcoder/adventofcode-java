package adventofcode.calendar.year2018.day23;

public class Octahedron {
    public static Octahedron parse(String str) {
        Point pos = null;
        Integer radius = null;
        for (String token : str.split(", ")) {
            String[] pair = token.split("=", 2);
            if (pair[0].equals("pos")) {
                pos = Point.parse(pair[1]);
            } else if (pair[0].equals("r")) {
                radius = Integer.parseInt(pair[1]);
            }
        }
        return new Octahedron(pos, radius);
    }

    public final Point pos;
    public final int radius;

    public Octahedron(Point pos, int radius) {
        this.pos = pos;
        this.radius = radius;
    }

    public boolean contains(Point point) {
        return pos.distanceTo(point) <= radius;
    }

    public boolean intersects(Box box) {
        int dMin = 0;
        if (pos.x < box.min.x) {
            dMin += box.min.x - pos.x;
        } else if (pos.x > box.max.x) {
            dMin += pos.x - box.max.x;
        }
        if (pos.y < box.min.y) {
            dMin += box.min.y - pos.y;
        } else if (pos.y > box.max.y) {
            dMin += pos.y - box.max.y;
        }
        if (pos.z < box.min.z) {
            dMin += box.min.z - pos.z;
        } else if (pos.z > box.max.z) {
            dMin += pos.z - box.max.z;
        }
        return dMin <= radius;
    }
}
