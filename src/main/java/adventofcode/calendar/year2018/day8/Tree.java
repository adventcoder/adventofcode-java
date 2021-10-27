package adventofcode.calendar.year2018.day8;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Tree {
    private final Tree[] children;
    private final int[] entries;

    public Tree(StringTokenizer tokens) {
        children = new Tree[Integer.parseInt(tokens.nextToken())];
        entries = new int[Integer.parseInt(tokens.nextToken())];
        for (int i = 0; i < children.length; i++) {
            children[i] = new Tree(tokens);
        }
        for (int i = 0; i < entries.length; i++) {
            entries[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    public int checksum() {
        int sum = 0;
        for (Tree child : children) {
            sum += child.checksum();
        }
        for (int n : entries) {
            sum += n;
        }
        return sum;
    }

    public int value() {
        int sum = 0;
        if (children.length == 0) {
            for (int n : entries) {
                sum += n;
            }
        } else {
            int[] values = new int[children.length];
            Arrays.fill(values, -1);
            for (int n : entries) {
                int i = n - 1;
                if (i >= 0 && i < children.length) {
                    if (values[i] == -1) {
                        values[i] = children[i].value();
                    }
                    sum += values[i];
                }
            }
        }
        return sum;
    }
}
