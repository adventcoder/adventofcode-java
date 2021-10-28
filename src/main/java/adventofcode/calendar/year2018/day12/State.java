package adventofcode.calendar.year2018.day12;

import java.util.Arrays;

public class State {
    public static State parseState(String line) {
        byte[] state = new byte[line.length()];
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
                state[i] = 1;
            }
        }
        return new State(0, state);
    }

    public static int parseRules(String chunk) {
        int rules = 0;
        for (String line : chunk.split("\n")) {
            if (line.charAt(9) == '#') {
                int rule = 0;
                for (int i = 0; i < 5; i++) {
                    if (line.charAt(i) == '#') {
                        rule |= 1 << i;
                    }
                }
                rules |= 1 << rule;
            }
        }
        return rules;
    }

    public final int min;
    public final byte[] state;

    public State(int min, byte[] state) {
        int start = 0;
        while (start < state.length && state[start] == 0) {
            start++;
        }
        int end = state.length;
        while (end > start && state[end - 1] == 0) {
            end--;
        }
        if (start > 0 || end < state.length) {
            this.min = min + start;
            this.state = Arrays.copyOfRange(state, start, end);
        } else {
            this.min = min;
            this.state = state;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(state.length);
        for (int i = 0; i < state.length; i++) {
            builder.append(state[i] == 1 ? '#' : '.');
        }
        return builder.toString();
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < state.length; i++) {
            count += state[i];
        }
        return count;
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1) {
                sum += min + i;
            }
        }
        return sum;
    }

    public byte getBit(int n) {
        if (n >= min && n - min < state.length) {
            return state[n - min];
        }
        return 0;
    }

    public State tick(int rules) {
        int newMin = min - 2;
        byte[] newState = new byte[state.length + 4];
        for (int n = newMin; n < newMin + newState.length; n++) {
            newState[n - newMin] = tick(n, rules);
        }
        return new State(newMin, newState);
    }

    public byte tick(int n, int rules) {
        int rule = 0;
        for (int i = 0; i < 5; i++) {
            rule |= getBit(n - 2 + i) << i;
        }
        return (byte) ((rules >>> rule) & 1);
    }
}
