package adventofcode.calendar.year2017.day7;

import adventofcode.framework.AbstractPart;

import java.util.*;

import static adventofcode.utils.Iterables.findFirst;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Map<String, List<String>> discs = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, Integer> weights = new HashMap<>();
        for (String line : input.split("\n")) {
            String[] pair = line.split("->", 2);
            String[] tokens = pair[0].trim().split("\\s+");
            String parent = tokens[0];
            weights.put(parent, Integer.parseInt(tokens[1].substring(1, tokens[1].length() - 1)));
            discs.put(parent, new ArrayList<>());
            if (pair.length > 1) {
                for (String child : pair[1].trim().split(",\\s+")) {
                    assert !parents.containsKey(child);
                    parents.put(child, parent);
                    discs.get(parent).add(child);
                }
            }
        }

        String root = findRoot(parents);

        Map<String, Integer> totalWeights = new HashMap<>(weights);
        accumulate(root, totalWeights, discs);

        String child = findIncorrectChild(root, totalWeights, discs);
        if (child == null) {
            return null;
        }
        String next = findIncorrectChild(child, totalWeights, discs);
        while (next != null) {
            child = next;
            next = findIncorrectChild(child, totalWeights, discs);
        }
        return weights.get(child) + findCorrectWeight(discs.get(parents.get(child)), totalWeights) - totalWeights.get(child);
    }

    private String findRoot(Map<String, String> parents) {
        String root = parents.values().iterator().next();
        while (parents.containsKey(root)) {
            root = parents.get(root);
        }
        return root;
    }

    private void accumulate(String root, Map<String, Integer> weights, Map<String, List<String>> discs) {
        int sum = weights.get(root);
        for (String child : discs.get(root)) {
            accumulate(child, weights, discs);
            sum += weights.get(child);
        }
        weights.put(root, sum);
    }

    private String findIncorrectChild(String root, Map<String, Integer> weights, Map<String, List<String>> discs) {
        int correctWeight = findCorrectWeight(discs.get(root), weights);
        return findFirst((child) -> weights.get(child) != correctWeight, discs.get(root));
    }

    private int findCorrectWeight(List<String> disc, Map<String, Integer> weights) {
        // Just look for two programs with the same weight, and use that.
        for (int i = 0; i < disc.size(); i++) {
            int a = weights.get(disc.get(i));
            for (int j = i + 1; j < disc.size(); j++) {
                int b = weights.get(disc.get(j));
                if (a == b) return a;
            }
        }
        throw new IllegalStateException("all children have different weights");
    }
}
