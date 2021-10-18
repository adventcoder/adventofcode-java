package adventofcode.calendar.year2019.day10;

import adventofcode.utils.IntMath;
import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field {
    private final String[] grid;
    private final List<Vector2D> positions;
    private final int[][] distance;

    public Field(String input) {
        grid = input.split("\n");
        positions = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (grid[y].charAt(x) == '#') {
                    positions.add(new Vector2D(x, y));
                }
            }
        }
        distance = new int[positions.size()][positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            distance[i][i] = 0;
            for (int j = i + 1; j < positions.size(); j++) {
                distance[i][j] = distance[j][i] = calculateDistance(grid, positions.get(i), positions.get(j));
            }
        }
    }

    public int size() {
        return positions.size();
    }

    public Vector2D getPosition(int i) {
        return positions.get(i);
    }

    private static int calculateDistance(String[] grid, Vector2D a, Vector2D b) {
        int count = 0;
        if (!a.equals(b)) {
            Vector2D dir = b.sub(a);
            dir.divEq(IntMath.gcd(dir.x, dir.y));
            for (Vector2D pos = new Vector2D(a); !pos.equals(b); pos.addEq(dir)) {
                if (grid[pos.y].charAt(pos.x) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    public Iterable<FieldView> views() {
        return this::viewIterator;
    }

    public Iterator<FieldView> viewIterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < positions.size();
            }

            @Override
            public FieldView next() {
                return new FieldView(Field.this, i++);
            }
        };
    }

    public int distance(int i, int j) {
        return distance[i][j];
    }

    public Vector2D direction(int i, int j) {
        return positions.get(j).sub(positions.get(i));
    }

    public int countVisible(int i) {
        int count = 0;
        for (int j = 0; j < positions.size(); j++) {
            if (distance[i][j] == 1) {
                count++;
            }
        }
        return count;
    }
}
