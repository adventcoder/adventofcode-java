package adventofcode.calendar.year2019.day25;

import adventofcode.framework.AbstractPart;

import java.util.Set;

//TODO: revisit
public class Part1 extends AbstractPart<String> {
    private static final Set<String> skipItems = Set.of("infinite loop", "giant electromagnet", "escape pod", "photons", "molten lava");

    @Override
    public String solve(String input) {
        Droid droid = new Droid(input);
        droid.takeAllItems(skipItems, null);
        return droid.trialAndError(null);
    }
}
