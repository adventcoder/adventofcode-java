package adventofcode.calendar.year2019.day20;

import adventofcode.utils.Vector2D;

public class HyperPoint {
    public final Vector2D pos;
    public final int floor;

    public HyperPoint(Vector2D pos, int floor) {
        this.pos = pos;
        this.floor = floor;
    }

    @Override
    public int hashCode() {
        return pos.hashCode() * 31 + floor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HyperPoint) {
            HyperPoint other = (HyperPoint) obj;
            return pos.equals(other.pos) && floor == other.floor;
        }
        return false;
    }
}
