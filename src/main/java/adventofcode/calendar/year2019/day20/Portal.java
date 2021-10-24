package adventofcode.calendar.year2019.day20;

public class Portal {
    public final String label;
    public final boolean inside;

    public Portal(String label, boolean inside) {
        this.label = label;
        this.inside = inside;
    }

    public Portal other() {
        return new Portal(label, !inside);
    }

    @Override
    public int hashCode() {
        return label.hashCode() * 31 + (inside ? 1 : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Portal) {
            Portal portal = (Portal) obj;
            return inside == portal.inside && label.equals(portal.label);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Portal{" +
                "label='" + label + '\'' +
                ", inside=" + inside +
                '}';
    }
}
