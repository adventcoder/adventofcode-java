package adventofcode.calendar.year2018.day11;

public class CachedGrid {
    private final int[][] table;

    public CachedGrid(int serialNumber, int size) {
        Grid grid = new Grid(serialNumber);
        table = new int[size + 1][size + 1];
        for (int y = 1; y <= size; y++) {
            for (int x = 1; x <= size; x++) {
                table[y][x] = table[y][x - 1] + grid.power(x, y);
            }
        }
        for (int x = 1; x <= size; x++) {
            for (int y = 2; y <= size; y++) {
                table[y][x] += table[y - 1][x];
            }
        }
    }

    public int power(int x, int y, int size) {
        return table[y + size - 1][x + size - 1] - table[y + size - 1][x - 1] - table[y - 1][x + size - 1] + table[y - 1][x - 1];
    }
}
