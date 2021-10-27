package adventofcode.calendar.year2019.day6;

import java.util.*;

public class OrbitMap {
    private Map<String, String> parents = new HashMap<>();
    private Map<String, Integer> heights = new HashMap<>();

    public OrbitMap(String input) {
        for (String line : input.split("\n")) {
            String[] orbit = line.split("\\)", 2);
            if (parents.get(orbit[1]) != null) {
                throw new IllegalArgumentException(orbit[1] + " already orbiting " + parents.get(orbit[1]) + ", can't orbit " + orbit[0]);
            }
            parents.put(orbit[1], orbit[0]);
        }
    }

    public Set<String> masses() {
        return parents.keySet();
    }

    public String parent(String mass) {
        return parents.get(mass);
    }

    public boolean isRoot(String mass) {
        return parents.get(mass) == null;
    }

    public int getHeight(String mass) {
        Integer height = heights.get(mass);
        if (height == null) {
            height = computeHeight(mass);
            heights.put(mass, height);
        }
        return height;
    }

    private int computeHeight(String mass) {
        return isRoot(mass) ? 0 : getHeight(parent(mass)) + 1;
    }

    public int distanceBetween(String a, String b) {
        return getHeight(a) + getHeight(b) - 2 * getHeight(lca(a, b));
    }

    public String lca(String a, String b) {
        while (getHeight(a) > getHeight(b)) {
            a = parent(a);
        }
        while (getHeight(b) > getHeight(a)) {
            b = parent(b);
        }
        while (!a.equals(b)) {
            a = parent(a);
            b = parent(b);
        }
        return a;
    }
}
