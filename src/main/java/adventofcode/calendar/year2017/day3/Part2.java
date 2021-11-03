package adventofcode.calendar.year2017.day3;

import adventofcode.framework.AbstractPart;

import java.util.ArrayList;
import java.util.List;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int n = Integer.parseInt(input);
        List<Integer> values = new ArrayList<>();
        values.add(1);
        int x = 0;
        int y = 0;
        int length = 1;
        int sign = 1;
        while (true) {
            for (int i = 0; i < length; i++) {
                x += sign;
                int newValue = computeValue(x, y, values);
                if (newValue > n) return newValue;
                values.add(newValue);
            }
            for (int i = 0; i < length; i++) {
                y -= sign;
                int newValue = computeValue(x, y, values);
                if (newValue > n) return newValue;
                values.add(newValue);
            }
            sign = -sign;
            length++;
        }
    }

    private int computeValue(int x, int y, List<Integer> values) {
        int sum = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int i = valueNumber(x + dx, y + dy) - 1;
                if (i < values.size()) sum += values.get(i);
            }
        }
        return sum;
    }

    private int valueNumber(int x, int y) {
        int r = Math.max(Math.abs(x), Math.abs(y));
        int d = 2 * r;
        int n = (d - 1) * (d - 1);
        if (x == r) {
            if (y == r) {
                n += 4 * d;
            } else {
                n += r - y;
            }
        } else if (y == -r) {
            n += d + r - x;
        } else if (x == -r) {
            n += 2 * d + r + y;
        } else {
            n += 3 * d + r + x;
        }
        return n;
    }
}
