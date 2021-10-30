package adventofcode.calendar.year2018.day21;

import adventofcode.calendar.year2018.day19.Process;
import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Process process = new Process(input);
        while (process.running()) { 
            // Run until there's a comparison with A, then return the value of the other register.
            int[] instr = process.currentInstruction();
            if (instr[0] == 15 && instr[2] == 0) {
                return process.reg[instr[1]];
            }
            process.step();
        }
        return null;
    }
}
