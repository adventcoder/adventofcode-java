package adventofcode.calendar.year2017.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static adventofcode.utils.Iterables.findFirst;

public class Program {
    public static Program parseRoot(String input) {
        Map<String, Program> parents = new HashMap<>();
        Map<String, Program> programs = new HashMap<>();
        for (String line : input.split("\n")) {
            String[] pair = line.split("->");
            Program program = new Program(pair[0]);
            programs.put(program.name, program);
            if (pair.length > 1) {
                for (String childName : pair[1].trim().split("\\s*,\\s*")) {
                    if (parents.containsKey(childName)) {
                        throw new IllegalArgumentException(childName + " has multiple parents: " + program.name + " and " + parents.get(childName));
                    }
                    parents.put(childName, program);
                }
            }
        }
        for (String childName : parents.keySet()) {
            Program child = programs.remove(childName);
            parents.get(child.name).disc.add(child);
        }
        if (programs.size() > 1) {
            throw new IllegalArgumentException("multiple root programs: " + programs.keySet());
        }
        return programs.values().iterator().next();
    }

    public final String name;
    public final int weight;
    public final List<Program> disc = new ArrayList<>();
    public int totalWeight;

    public Program(String str) {
        String[] tokens = str.trim().split("\\s+");
        name = tokens[0];
        weight = Integer.parseInt(tokens[1].substring(1, tokens[1].length() - 1));
    }

    public void computeTotalWeight() {
        totalWeight = weight;
        for (Program child : disc) {
            child.computeTotalWeight();
            totalWeight += child.totalWeight;
        }
    }

    public Program findIncorrectChild() {
        int correctTotalWeight = findCorrectTotalWeight();
        return findFirst((child) -> child.totalWeight != correctTotalWeight, disc);
    }

    public int findCorrectTotalWeight() {
        // Just look for two programs with the same weight, and use that.
        for (int i = 0; i < disc.size(); i++) {
            for (int j = i + 1; j < disc.size(); j++) {
                if (disc.get(i).totalWeight == disc.get(j).totalWeight) {
                    return disc.get(i).totalWeight;
                }
            }
        }
        throw new IllegalStateException();
    }
}
