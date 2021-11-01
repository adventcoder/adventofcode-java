package adventofcode.calendar.year2018.day23;

import java.util.ArrayList;
import java.util.List;

public class Box {
    public static Box bounding(List<Octahedron> octs) {
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        int zMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        int zMax = Integer.MIN_VALUE;
        for (Octahedron oct : octs) {
            xMin = Math.min(xMin, oct.pos.x - oct.radius);
            yMin = Math.min(yMin, oct.pos.y - oct.radius);
            zMin = Math.min(zMin, oct.pos.z - oct.radius);
            xMax = Math.max(xMax, oct.pos.x + oct.radius);
            yMax = Math.max(yMax, oct.pos.y + oct.radius);
            zMax = Math.max(zMax, oct.pos.z + oct.radius);
        }
        return new Box(new Point(xMin, yMin, zMin), new Point(xMax, yMax, zMax));
    }

    public final Point min;
    public final Point max;

    public Box(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public List<Box> split() {
        List<Box> result = new ArrayList<>();
        Point mid = new Point((min.x + max.x) / 2, (min.y + max.y) / 2, (min.z + max.z) / 2);
        result.add(new Box(min, mid));
        if (min.x != max.x) {
            result.add(new Box(new Point(mid.x + 1, min.y, min.z), new Point(max.x, mid.y, mid.z)));
        }
        if (min.y != max.y) {
            result.add(new Box(new Point(min.x, mid.y + 1, min.z), new Point(mid.x, max.y, mid.z)));
            if (min.x != max.x) {
                result.add(new Box(new Point(mid.x + 1, mid.y + 1, min.z), new Point(max.x, max.y, mid.z)));
            }
        }
        if (min.z != max.z) {
            result.add(new Box(new Point(min.x, min.y, mid.z + 1), new Point(mid.x, mid.y, max.z)));
            if (min.x != max.x) {
                result.add(new Box(new Point(mid.x + 1, min.y, mid.z + 1), new Point(max.x, mid.y, max.z)));
            }
            if (min.y != max.y) {
                result.add(new Box(new Point(min.x, mid.y + 1, mid.z + 1), new Point(mid.x, max.y, max.z)));
                if (min.x != max.x) {
                    result.add(new Box(new Point(mid.x + 1, mid.y + 1, mid.z + 1), new Point(max.x, max.y, max.z)));
                }
            }
        }
        return result;
    }
}
