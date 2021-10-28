package adventofcode.calendar.year2018.day11;

public class Grid {
    private final int serialNumber;

    public Grid(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int power(int x, int y) {
        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel += serialNumber;
        powerLevel *= rackId;
        return powerLevel / 100 % 10 - 5;
    }

    public int power(int x, int y, int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += power(x + i, y + j);
            }
        }
        return sum;
    }
}
