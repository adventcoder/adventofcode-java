package adventofcode.calendar.year2019.day14;

import java.math.BigInteger;
import java.util.*;

public class Reactions {
    public static Map<String, BigInteger> parseCompound(String str) {
        Map<String, BigInteger> atoms = new HashMap<>();
        for (String token : str.split(",")) {
            Map.Entry<String, BigInteger> atom = parseAtom(token);
            atoms.put(atom.getKey(), atom.getValue());
        }
        return atoms;
    }

    public static Map.Entry<String, BigInteger> parseAtom(String str) {
        String[] parts = str.trim().split("\\s+");
        return new AbstractMap.SimpleEntry<>(parts[1], new BigInteger(parts[0]));
    }

    private final Map<String, Map<String, BigInteger>> outputs = new HashMap<>();
    private final Map<String, BigInteger> inputs = new HashMap<>();
    public final List<String> sortedInputs = new ArrayList<>();

    public Reactions(String input) {
        for (String line : input.split("\n")) {
            String[] pair = line.split("=>", 2);
            Map<String, BigInteger> lhs = parseCompound(pair[0]);
            Map.Entry<String, BigInteger> rhs = parseAtom(pair[1]);
            inputs.put(rhs.getKey(), rhs.getValue());
            outputs.put(rhs.getKey(), lhs);
        }
        for (String name : inputs.keySet()) {
            addSortedInput(name);
        }
        Collections.reverse(sortedInputs);
    }

    private void addSortedInput(String name) {
        if (!sortedInputs.contains(name)) {
            for (String newName : outputs.get(name).keySet()) {
                if (inputs.containsKey(newName)) {
                    addSortedInput(newName);
                }
            }
            sortedInputs.add(name);
        }
    }

    public void applyRoundingUp(String name, Map<String, BigInteger> compound) {
        if (compound.containsKey(name)) {
            BigInteger n = compound.get(name).add(inputs.get(name).subtract(BigInteger.ONE)).divide(inputs.get(name));
            compound.remove(name);
            for (String newName : outputs.get(name).keySet()) {
                compound.put(newName, compound.getOrDefault(newName, BigInteger.ZERO).add(n.multiply(outputs.get(name).get(newName))));
            }
        }
    }
}
