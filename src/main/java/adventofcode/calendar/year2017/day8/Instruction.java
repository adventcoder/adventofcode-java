package adventofcode.calendar.year2017.day8;

import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Instruction {
    private interface IntBinaryPredicate {
        boolean test(int a, int b);
    }

    private static final Map<String, IntBinaryPredicate> predicates = Map.ofEntries(
            Map.entry(">", (a, b) -> a > b),
            Map.entry("<", (a, b) -> a < b),
            Map.entry(">=", (a, b) -> a >= b),
            Map.entry("<=", (a, b) -> a <= b),
            Map.entry("==", (a, b) -> a == b),
            Map.entry("!=", (a, b) -> a != b));

    private static final Map<String, IntBinaryOperator> operators = Map.ofEntries(
            Map.entry("inc", Integer::sum),
            Map.entry("dec", (a, b) -> a - b));

    public final String[] args;

    public Instruction(String line) {
        this.args = line.split("\\s+");
    }

    public void apply(Map<String, Integer> values) {
        if (test(values)) update(values);
    }

    public boolean test(Map<String, Integer> values) {
        return predicates.get(args[5]).test(values.getOrDefault(args[4], 0), Integer.parseInt(args[6]));
    }

    public int update(Map<String, Integer> values) {
        int value = operators.get(args[1]).applyAsInt(values.getOrDefault(args[0], 0), Integer.parseInt(args[2]));
        values.put(args[0], value);
        return value;
    }
}
