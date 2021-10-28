package adventofcode.calendar.year2018.day13;

import adventofcode.utils.Vector2D;

public class Cart {
    private final String[] track;
    public final Vector2D pos;
    public final Vector2D dir;
    private int state = 0;

    public Cart(String[] track, Vector2D pos) {
        this.track = track;
        this.pos = pos;
        if (charAt() == '^') {
            dir = new Vector2D(0, -1);
        } else if (charAt() == 'v') {
            dir = new Vector2D(0, 1);
        } else if (charAt() == '<') {
            dir = new Vector2D(-1, 0);
        } else if (charAt() == '>') {
            dir = new Vector2D(1, 0);
        } else {
            dir = new Vector2D(0, 0);
        }
    }

    private char charAt() {
        return track[pos.y].charAt(pos.x); 
    }

    public void tick() {
        pos.addEq(dir);
        if (charAt() == '/') {
            if (dir.y == 0) {
                dir.rotateLeft();
            } else {
                dir.rotateRight();
            }
        } else if (charAt() == '\\') {
            if (dir.y == 0) {
                dir.rotateRight();
            } else {
                dir.rotateLeft();
            }
        } else if (charAt() == '+') {
            if (state == 0) {
                dir.rotateLeft();
            } else if (state == 2) {
                dir.rotateRight();
            }
            state = (state + 1) % 3;
        }
    }
}
