package adventofcode.calendar.year2018.day13;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

public class Part2 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        Track track = new Track(input);
        while (track.alive.size() > 1) {
            track.tick();
        }
        Vector2D pos = track.alive.keySet().iterator().next();
        return pos.x + "," + pos.y;
    }
}
