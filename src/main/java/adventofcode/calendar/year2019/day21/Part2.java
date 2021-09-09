package adventofcode.calendar.year2019.day21;

import adventofcode.calendar.year2019.common.ASCIIComputer;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        ASCIIComputer computer = new ASCIIComputer(input);
        // Jump as soon as a hole is detected as long as it's possible to land.
        // And it's either possible to move or jump again after landing.
        //
        // J = (!A | !B | !C) & D & (E | H)
        //
        computer.writeLine("NOT A J");
        computer.writeLine("NOT B T");
        computer.writeLine("OR T J");
        computer.writeLine("NOT C T");
        computer.writeLine("OR T J");
        computer.writeLine("AND D J");
        computer.writeLine("NOT E T");
        computer.writeLine("NOT T T");
        computer.writeLine("OR H T");
        computer.writeLine("AND T J");
        computer.writeLine("RUN");
        return computer.nextUnhandledOutput();
    }
}
