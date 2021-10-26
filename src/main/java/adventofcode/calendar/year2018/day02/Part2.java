package adventofcode.calendar.year2018.day02;

import adventofcode.framework.AbstractPart;

import java.util.HashMap;
import java.util.Map;

public class Part2 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        Map<String, String> map = new HashMap<>();
        for (String boxId : input.split("\n")) {
            StringBuilder mask = new StringBuilder(boxId);
            for (int i = 0; i < mask.length(); i++) {
                mask.setCharAt(i, '_');
                if (map.put(mask.toString(), boxId) != null) {
                    return boxId.substring(0, i) + boxId.substring(i + 1);
                }
                mask.setCharAt(i, boxId.charAt(i));
            }
        }
        return null;
    }
}
