package adventofcode.calendar.year2019.day10;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntMath;
import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] grid = input.split("\n");

        List<Vector2D> stroids = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (grid[y].charAt(x) == '#') {
                    stroids.add(new Vector2D(x, y));
                }
            }
        }
        boolean[][] visible = new boolean[stroids.size()][stroids.size()];
        for (int i = 0; i < stroids.size(); i++) {
            visible[i][i] = true;
            for (int j = i + 1; j < stroids.size(); j++) {
                visible[i][j] = isVisible(stroids.get(i), stroids.get(j), grid);
                visible[j][i] = visible[i][j];
            }
        }

        Vector2D bestStroid = null;
        Integer bestCount = null;
        for (int i = 0; i < stroids.size(); i++) {
            int count = 0;
            for (int j = 0; j < stroids.size(); j++) {
                if (i == j) continue;
                if (visible[i][j]) count++;
            }
            if (bestCount == null || count > bestCount) {
                bestStroid = stroids.get(i);
                bestCount = count;
            }
        }
        System.out.println(bestStroid);
        return bestCount;
    }

    private boolean isVisible(Vector2D a, Vector2D b, String[] grid) {
        if (a.equals(b)) return false;
        Vector2D step = b.sub(a);
        step.divEq(IntMath.gcd(step.x, step.y));
        for (Vector2D p = a.add(step); !p.equals(b); p.addEq(step)) {
            if (grid[p.y].charAt(p.x) == '#') return false;
        }
        return true;
    }
}
