package adventofcode.calendar.year2017.day7;

import adventofcode.framework.AbstractPart;

import java.util.HashMap;
import java.util.Map;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        Map<String, String> parents = new HashMap<>();
        for (String line : input.split("\n")) {
            String[] pair = line.split("->", 2);
            String parent = pair[0].trim().split("\\s+")[0];
            if (pair.length > 1) {
                for (String child : pair[1].trim().split(",\\s+")) {
                    assert !parents.containsKey(child);
                    parents.put(child, parent);
                }
            }
        }
        String root = parents.values().iterator().next();
        while (parents.containsKey(root)) {
            root = parents.get(root);
        }
        return root;
    }
}
