package adventofcode.calendar.year2018.day16;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.util.*;

import static adventofcode.utils.Iterables.findFirst;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        String[] chunks = input.split("\n\n\n\n");
        Operator[] ops = assignOps(chunks[0]);
        if (ops == null) return null;
        int[] reg = new int[4];
        run(chunks[1], reg, ops);
        return reg[0];
    }

    private Operator[] assignOps(String samples) {
        Map<Operator, Set<Integer>> candidates = new HashMap<>(Operator.values.size());
        for (String chunk : samples.split("\n\n")) {
            Sample sample = new Sample(chunk);
            for (Operator op : Operator.values) {
                if (sample.test(op)) {
                    int code = sample.instr[0];
                    if (!candidates.containsKey(op)) candidates.put(op, new HashSet<>());
                    candidates.get(op).add(code);
                }
            }
        }
        // There's probably better ways of doing this.
        Operator[] ops = new Operator[Operator.values.size()];
        while (!candidates.isEmpty()) {
            Operator op = findFirst((key) -> candidates.get(key).size() == 1, candidates.keySet());
            if (op == null) return null;
            int code = candidates.remove(op).iterator().next();
            ops[code] = op;
            Iterator<Set<Integer>> it = candidates.values().iterator();
            while (it.hasNext()) {
                Set<Integer> codes = it.next();
                codes.remove(code);
                if (codes.isEmpty()) {
                    it.remove();
                }
            }
        }
        return ops;
    }

    private void run(String program, int[] reg, Operator[] ops) {
        for (String line : program.split("\n")) {
            int[] instr = IntArray.parse(line, "\\s+");
            ops[instr[0]].apply(instr, reg);
        }
    }
}
