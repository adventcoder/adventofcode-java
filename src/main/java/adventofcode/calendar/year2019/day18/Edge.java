package adventofcode.calendar.year2019.day18;

class Edge {
    public final int doors;
    public final int keys;
    public final int steps;

    public Edge(int doors, int keys, int steps) {
        this.doors = doors;
        this.keys = keys;
        this.steps = steps;
    }

    public Edge add(char c) {
        int newDoors = (c >= 'A' && c <= 'Z') ? 1 << (c - 'A') : 0;
        int newKeys = (c >= 'a' && c <= 'z') ? 1 << (c - 'a') : 0;
        return new Edge(doors | newDoors, keys | newKeys, steps + 1);
    }
}
