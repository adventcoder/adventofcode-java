package adventofcode.calendar.year2018.day13;

import adventofcode.utils.Vector2D;

import java.util.*;

public class Track {
    public final Map<Vector2D, Cart> alive = new HashMap<>();
    public final List<Vector2D> dead = new ArrayList<>();

    public Track(String input) {
        String[] track = input.split("\n");
        for (int y = 0; y < track.length; y++) {
            for (int x = 0; x < track[y].length(); x++) {
                if ("<>^v".indexOf(track[y].charAt(x)) >= 0) {
                    Cart cart = new Cart(track, new Vector2D(x, y));
                    alive.put(cart.pos, cart);
                }
            }
        }
    }

    public void tick() {
        List<Vector2D> positions = new ArrayList<>(alive.keySet());
        positions.sort(this::comparePositions);
        for (Vector2D pos : positions) {
            if (alive.containsKey(pos)) {
                Cart cart = alive.remove(pos);
                cart.tick();
                Cart other = alive.remove(cart.pos);
                if (other != null) {
                    dead.add(cart.pos);
                } else {
                    alive.put(cart.pos, cart);
                }
            }
        }
    }

    private int comparePositions(Vector2D a, Vector2D b) {
        int cmp = Integer.compare(a.y, b.y);
        if (cmp == 0) {
            cmp = Integer.compare(a.x, b.x);
        }
        return cmp;
    }
}
