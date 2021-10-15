package adventofcode.calendar.year2019.day21;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

import java.math.BigInteger;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BufferedIntcode droid = new BufferedIntcode(input);
        // Jump as soon as a hole is detected as long as it's possible to land.
        // And it's either possible to move or jump again after landing.
        //
        // J = (!A | !B | !C) & D & (E | H)
        //
        droid.writeLine("NOT A J");
        droid.writeLine("NOT B T");
        droid.writeLine("OR T J");
        droid.writeLine("NOT C T");
        droid.writeLine("OR T J");
        droid.writeLine("AND D J");
        droid.writeLine("NOT E T");
        droid.writeLine("NOT T T");
        droid.writeLine("OR H T");
        droid.writeLine("AND T J");
        droid.writeLine("RUN");
        while (droid.hasNextCodePoint()) {
            droid.next();
        }
        return droid.next();
    }
}
