package adventofcode.calendar.year2018.day22;

import adventofcode.utils.Vector2D;

import java.util.*;

public class Cave {
    private static final int xFactor = 16807;
    private static final int yFactor = 48271;
    private static final int modulus = 20183;

    public static Cave parse(String input) {
        Integer depth = null;
        Vector2D target = null;
        for (String line : input.split("\n")) {
            String[] pair = line.split(":\\s*", 2);
            if (pair[0].equals("depth")) {
                depth = Integer.parseInt(pair[1]);
            } else if (pair[0].equals("target")) {
                String[] parts = pair[1].split(",");
                target = new Vector2D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }
        }
        return new Cave(depth, target);
    }

    public final int depth;
    public final Vector2D target;
    private Map<Vector2D, Integer> erosionLevel = new HashMap<>();

    public Cave(int depth, Vector2D target) {
        this.depth = depth;
        this.target = target;
    }

    public int geologicalIndex(Vector2D pos) {
        if ((pos.x == 0 && pos.y == 0) || pos.equals(target)) {
            return 0;
        } else if (pos.y == 0) {
            return xFactor * pos.x;
        } else if (pos.x == 0) {
            return yFactor * pos.y;
        } else {
            return erosionLevel(pos.left()) * erosionLevel(pos.up());
        }
    }

    public int erosionLevel(Vector2D pos) {
        if (!erosionLevel.containsKey(pos)) {
            erosionLevel.put(pos, (geologicalIndex(pos) + depth) % modulus);
        }
        return erosionLevel.get(pos);
    }

    public int type(Vector2D pos) {
        return erosionLevel(pos) % 3;
    }

    public int riskLevel() {
        int riskLevel = 0;
        for (int y = 0; y <= target.y; y++) {
            for (int x = 0; x <= target.x; x++) {
                riskLevel += type(new Vector2D(x, y));
            }
        }
        return riskLevel;
    }

    @Override
    public String toString() {
        List<StringBuilder> lines = new ArrayList<>();
        for (int y = 0; y <= target.y + 5; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x <= target.x + 5; x++) {
                if (x == 0 && y == 0) {
                    line.append('M');
                } else if (x == target.x && y == target.y) {
                    line.append('T');
                } else {
                    line.append(".=|".charAt(type(new Vector2D(x, y))));
                }
            }
            lines.add(line);
        }
        return String.join("\n", lines);
    }
}
