package adventofcode.calendar.year2018.day21;

import adventofcode.calendar.year2018.day19.Process;
import adventofcode.framework.AbstractPart;

import java.util.HashSet;
import java.util.Set;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Process process = new Process(input);
        // The values being compared against A will eventually repeat. Find the last one before they start repeating.
        // This is super slow, btw.
        Set<Integer> seen = new HashSet<>();
        int last = 0;
        while (process.running()) { 
            int[] instr = process.currentInstruction();
            if (instr[0] == 15 && instr[2] == 0) {
                if (!seen.add(process.reg[instr[1]])) {
                    return last;
                }
                last = process.reg[instr[1]];
            }
            process.step();
        }
        return null;
    }
}
