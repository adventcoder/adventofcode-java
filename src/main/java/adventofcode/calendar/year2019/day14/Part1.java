package adventofcode.calendar.year2019.day14;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;
import java.util.Map;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Reactions reactions = new Reactions(input);
        Map<String, BigInteger> compound = Reactions.parseCompound("1 FUEL");
        for (String name : reactions.sortedInputs) {
            reactions.applyRoundingUp(name, compound);
        }
        return compound.get("ORE");
    }
}
