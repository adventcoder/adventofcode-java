package adventofcode.calendar.year2019.day20;

public class HyperPortal {
    public final Portal portal;
    public final int floor;

    public HyperPortal(Portal portal, int floor) {
        this.portal = portal;
        this.floor = floor;
    }

    public HyperPortal(String label, boolean inside, int floor) {
        this(new Portal(label, inside), floor);
    }

    @Override
    public int hashCode() {
        return portal.hashCode() * 31 + floor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HyperPortal) {
            HyperPortal other = (HyperPortal) obj;
            return portal.equals(other.portal) && floor == other.floor;
        }
        return false;
    }
}
