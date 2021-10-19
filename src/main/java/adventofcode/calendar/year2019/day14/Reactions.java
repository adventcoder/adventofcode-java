package adventofcode.calendar.year2019.day14;

import adventofcode.utils.IntMath;

import java.math.BigInteger;
import java.util.*;

/*

2 FUEL = 44 C + 5 D + 1 E + 29 A + 9 F + 48 G
2 C = 7 B + 7 H
8 D = 3 B + 7 A + 5 G + 10 H
9 E = 12 G + 1 F + 8 H
5 A = 157 ORE
2 F = 165 ORE
5 G = 177 ORE
6 B = 165 ORE
7 H = 179 ORE 


5 FUEL, 1 BLARG
(2 44 C + 5 D + 1 E + 29 A + 9 F + 48 G), 1 FUEL, 1 BLARG



 */
public class Reactions {
    private final Map<String, Map<String, BigInteger>> outputs = new HashMap<>();
    private final Map<String, BigInteger> inputs = new HashMap<>();

    public Reactions(String input) {
        for (String line : input.split("\n")) {
            String[] pair = line.split("=>", 2);
            Map<String, BigInteger> lhs = parseCompound(pair[0]);
            Map.Entry<String, BigInteger> rhs = parseAtom(pair[1]);
            inputs.put(rhs.getKey(), rhs.getValue());
            outputs.put(rhs.getKey(), lhs);
        }
    }
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

    public List<String> sortedInputs() {
        List<String> sorted = new ArrayList<>();
        for (String input : inputs.keySet()) {
            addInputs(input, sorted);
        }
        Collections.reverse(sorted);
        return sorted;
    }

    private void addInputs(String name, List<String> sorted) {
        if (!sorted.contains(name)) {
            for (String newName : outputs.get(name).keySet()) {
                if (inputs.containsKey(newName)) {
                    addInputs(newName, sorted);
                }
            }
            sorted.add(name);
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
