package adventofcode.calendar.year2019.day21;

import adventofcode.calendar.year2019.common.ASCIIComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        ASCIIComputer computer = new ASCIIComputer(input);
        // Jump as soon as a hole is detected as long as it's possible to land:
        //
        // J = (!A | !B | !C) & D
        //
        computer.writeLine("NOT A J");
        computer.writeLine("NOT B T");
        computer.writeLine("OR T J");
        computer.writeLine("NOT C T");
        computer.writeLine("OR T J");
        computer.writeLine("AND D J");
        computer.writeLine("WALK");
        return computer.nextUnhandledOutput();
    }
}
