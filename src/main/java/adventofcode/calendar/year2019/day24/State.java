package adventofcode.calendar.year2019.day24;

public class State {
    private static final int radius = 2;
    private static final int size = 2 * radius + 1;

    public static int parse(String input) {
        int state = 0;
        String[] lines = input.split("\n");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (lines[y].charAt(x) == '#') {
                    state = setBug(state, x, y);
                }
            }
        }
        return state;
    }

    public static int setBug(int state, int x, int y) {
        return state | (1 << (y * size + x));
    }

    public static int countBugs(int state, int x, int y) {
        return (state >>> (y * size + x)) & 1;
    }

    public static int countBugsY(int state, int y) {
        int count = 0;
        state >>>= y * size;
        for (int x = 0; x < size; x++) {
            count += state & 1;
            state >>>= 1;
        }
        return count;
    }

    public static int countBugsX(int state, int x) {
        int count = 0;
        state >>>= x;
        for (int y = 0; y < size; y++) {
            count += state & 1;
            state >>>= size;
        }
        return count;
    }

    public static int tick(int state) {
        int newState = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int count = 0;
                if (y > 0) count += countBugs(state, x, y - 1);
                if (x > 0) count += countBugs(state, x - 1, y);
                if (x < size - 1) count += countBugs(state, x + 1, y);
                if (y < size - 1) count += countBugs(state, x, y + 1);
                if (count == 1 || (countBugs(state, x, y) == 0 && count == 2)) {
                    newState = setBug(newState, x, y);
                }
            }
        }
        return newState;
    }

    public static int tick(int prevState, int state, int nextState) {
        int newState = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (x == radius && y == radius) continue;
                int count = 0;
                if (y == 0) {
                    count += countBugs(prevState, radius, radius - 1);
                } else if (x == radius && y == radius + 1) {
                    count += countBugsY(nextState, size - 1);
                } else {
                    count += countBugs(state, x, y - 1);
                }
                if (x == 0) {
                    count += countBugs(prevState, radius - 1, radius);
                } else if (x == radius + 1 && y == radius) {
                    count += countBugsX(nextState, size - 1);
                } else {
                    count += countBugs(state, x - 1, y);
                }
                if (x == size - 1) {
                    count += countBugs(prevState, radius + 1, radius);
                } else if (x == radius - 1 && y == radius) {
                    count += countBugsX(nextState, 0);
                } else {
                    count += countBugs(state, x + 1, y);
                }
                if (y == size - 1) {
                    count += countBugs(prevState, radius, radius + 1);
                } else if (x == radius && y == radius - 1) {
                    count += countBugsY(nextState, 0);
                } else {
                    count += countBugs(state, x, y + 1);
                }
                if (count == 1 || (countBugs(state, x, y) == 0 && count == 2)) {
                    newState = setBug(newState, x, y);
                }
            }
        }
        return newState;
    }
}
