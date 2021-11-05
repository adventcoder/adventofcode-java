package adventofcode.calendar.year2017.day12;

import adventofcode.utils.IntArray;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int[][] neighbourIds;

    public Graph(String input) {
        String[] lines = input.split("\n");
        neighbourIds = new int[lines.length][];
        for (String line : lines) {
            String[] pair = line.split("<->", 2);
            int id = Integer.parseInt(pair[0].trim());
            neighbourIds[id] = IntArray.parse(pair[1].trim(), ",\\s*");
        }
    }

    public int findGroupSize(int id) {
        return findGroupSize(id, new boolean[neighbourIds.length]);
    }

    public List<Integer> findGroupSizes() {
        boolean[] seen = new boolean[neighbourIds.length];
        List<Integer> groupSizes = new ArrayList<>();
        for (int id = 0; id < neighbourIds.length; id++) {
            if (!seen[id]) {
                groupSizes.add(findGroupSize(id, seen));
            }
        }
        return groupSizes;
    }

    private int findGroupSize(int id, boolean[] seen) {
        if (seen[id]) return 0;
        seen[id] = true;
        int size = 1;
        for (int neighbourId : neighbourIds[id]) {
            size += findGroupSize(neighbourId, seen);
        }
        return size;
    }
}
