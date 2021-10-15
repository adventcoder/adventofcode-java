package adventofcode.calendar.year2019.day21;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BufferedIntcode droid = new BufferedIntcode(input);
        // Jump as soon as a hole is detected as long as it's possible to land:
        //
        // J = (!A | !B | !C) & D
        //
        droid.writeLine("NOT A J");
        droid.writeLine("NOT B T");
        droid.writeLine("OR T J");
        droid.writeLine("NOT C T");
        droid.writeLine("OR T J");
        droid.writeLine("AND D J");
        droid.writeLine("WALK");
        while (droid.hasNextCodePoint()) {
            droid.next();
        }
        return droid.next();
    }
}
