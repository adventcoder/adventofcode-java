package adventofcode.calendar.year2019.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hull {
    private Map<Object, BigInteger> colors = new HashMap<>();
    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;

    public int size() {
        return colors.size();
    }

    public BigInteger getColor(int x, int y) {
        return colors.getOrDefault(buildKey(x, y), BigInteger.ZERO);
    }

    public void putColor(int x, int y, BigInteger color) {
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        colors.put(buildKey(x, y), color);
    }

    @Override
    public String toString() {
        List<StringBuilder> lines = new ArrayList<>();
        for (int y = minY; y <= maxY; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = minX; x <= maxX; x++) {
                line.append(getColor(x, y).equals(BigInteger.ZERO) ? ' ' : '#');
            }
            lines.add(line);
        }
        return String.join("\n", lines);
    }

    private Object buildKey(int x, int y) {
        return (Short.toUnsignedInt((short) x) << 16) | Short.toUnsignedInt((short) y);
    }
}
