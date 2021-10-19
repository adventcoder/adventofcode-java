package adventofcode.calendar.year2019.day14;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Reactions reactions = new Reactions(input);
        return fuelForOre(reactions, BigInteger.valueOf(1000000000000L));
    }

    private BigInteger fuelForOre(Reactions reactions, BigInteger ore) {
        BigInteger minFuel = ore.divide(oreForFuel(reactions, BigInteger.ONE));
        BigInteger maxFuel = ore;
        while (maxFuel.subtract(minFuel).compareTo(BigInteger.ONE) > 0) {
            BigInteger fuel = minFuel.add(maxFuel).shiftRight(1);
            if (oreForFuel(reactions, fuel).compareTo(ore) > 0) {
                maxFuel = fuel.subtract(BigInteger.ONE);
            } else {
                minFuel = fuel;
            }
        }
        if (minFuel.equals(maxFuel)) return minFuel;
        return oreForFuel(reactions, maxFuel).compareTo(ore) > 0 ? minFuel : maxFuel;
    }

    private BigInteger oreForFuel(Reactions reactions, BigInteger fuel) {
        Map<String, BigInteger> compound = new HashMap<>();
        compound.put("FUEL", fuel);
        for (String name : reactions.sortedInputs()) {
            reactions.applyRoundingUp(name, compound);
        }
        return compound.get("ORE");
    }
}
