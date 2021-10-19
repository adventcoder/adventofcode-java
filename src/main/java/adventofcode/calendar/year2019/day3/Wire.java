package adventofcode.calendar.year2019.day3;

import java.util.ArrayList;
import java.util.List;

public class Wire {
    public static List<Segment> parseSegments(String wire) {
        List<Segment> segments = new ArrayList<>();
        Point start = new Point(0, 0, 0);
        for (String token : wire.split(",")) {
            Segment segment = Segment.parse(start, token);
            segments.add(segment);
            start = segment.end();
        }
        return segments;
    }

    public static List<Segment> cross(String[] wires) {
        List<Segment> result = null;
        for (String wire : wires) {
            if (result == null) {
                result = parseSegments(wire);
            } else {
                result = cross(result, parseSegments(wire));
            }
        }
        return result;
    }

    private static List<Segment> cross(List<Segment> as, List<Segment> bs) {
        List<Segment> cs = new ArrayList<>();
        for (Segment a : as) {
            for (Segment b : bs) {
                Segment c = a.cross(b);
                if (c != null) {
                    cs.add(c);
                }
            }
        }
        return cs;
    }
}
