package adventofcode.calendar.year2018.day13;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        Track track = new Track(input);
        while (track.dead.isEmpty()) {
            track.tick();
        }
        Vector2D pos = track.dead.get(0);
        return pos.x + "," + pos.y;
    }
}
