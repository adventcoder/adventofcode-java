package adventofcode.calendar.year2017.day11;

public class HexPoint {
    private int ne;
    private int se;

    public void add(String dir) {
        if (dir.equals("n")) {
            ne++;
            se--;
        } else if (dir.equals("s")) {
            se++;
            ne--;
        } else if (dir.equals("ne")) {
            ne++;
        } else if (dir.equals("nw")) {
            se--;
        } else if (dir.equals("sw")) {
            ne--;
        } else if (dir.equals("se")) {
            se++;
        }
    }

    public int abs() {
        return (Math.abs(ne) + Math.abs(se) + Math.abs(ne + se)) / 2;
    }
}
