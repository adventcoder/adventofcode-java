package adventofcode.calendar.year2019.day18;

public class State {
    public final char node;
    public final int keys;

    public State(char node, int keys) {
        this.node = node;
        this.keys = keys;
    }

    public boolean hasAllKeys(int targetKeys) {
        return (keys & targetKeys) == targetKeys;
    }

    @Override
    public int hashCode() {
        return node * 31 + keys;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State state = (State) obj;
            return node == state.node && keys == state.keys;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("node: ").append(node);
        if (keys != 0) {
            builder.append(", keys: ");
            int remainingKeys = keys;
            for (int i = 0; remainingKeys != 0; i++) {
                if ((remainingKeys & 1) == 1) {
                    builder.append((char) ('a' + i));
                }
                remainingKeys >>>= 1;
            }
        }
        return builder.toString();
    }
}
