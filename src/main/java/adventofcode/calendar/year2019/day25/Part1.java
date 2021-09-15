package adventofcode.calendar.year2019.day25;

import adventofcode.calendar.year2019.common.ASCIIComputer;
import adventofcode.framework.AbstractPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Part1 extends AbstractPart<String> {
    private static final Set<String> badItems = Set.of("infinite loop", "giant electromagnet", "escape pod", "photons", "molten lava");

    @Override
    public String solve(String input) {
        Droid droid = new Droid(new ASCIIComputer(input));
        takeAllItems(droid, null, badItems);
        return trialAndError(droid, null);
    }

    private void takeAllItems(Droid droid, Dir skipDir, Set<String> skipItems) {
        // System.out.println("== " + droid.room + " ==");
        // System.out.println(droid.roomDescription);
        // System.out.println("Items here: " + droid.itemsHere);
        // System.out.println("Doors here lead: " + droid.doorsHereLead);
        // System.out.println();
        for (String item : new ArrayList<>(droid.itemsHere)) {
            if (!skipItems.contains(item)) {
                droid.take(item);
            }
        }
        if (!droid.room.equals("Security Checkpoint")) {
            for (Dir dir : droid.doorsHereLead) {
                if (dir == skipDir) continue;
                if (droid.move(dir)) {
                    takeAllItems(droid, dir.opposite(), skipItems);
                    if (!droid.move(dir.opposite())) {
                        throw new IllegalStateException("stuck!");
                    }
                }
            }
        }
    }

    private String trialAndError(Droid droid, Dir skipDir) {
        if (droid.room.equals("Security Checkpoint")) {
            List<String> items = droid.inv();
            int mask = (1 << items.size()) - 1;
            for (int i = 0; i < (1 << items.size()); i++) {
                for (Dir dir : droid.doorsHereLead) {
                    if (dir == skipDir) continue;
                    String password = droid.moveFinal(dir);
                    if (password != null) return password;
                }
                int flipIndex = Integer.numberOfTrailingZeros(~i);
                if ((mask & (1 << flipIndex)) == 0) {
                    droid.take(items.get(flipIndex));
                } else {
                    droid.drop(items.get(flipIndex));
                }
                mask ^= 1 << flipIndex;
            }
        } else {
            for (Dir dir : droid.doorsHereLead) {
                if (dir == skipDir) continue;
                if (droid.move(dir)) {
                    String password = trialAndError(droid, dir.opposite());
                    if (password != null) return password;
                    if (!droid.move(dir.opposite())) {
                        throw new IllegalStateException("stuck!");
                    }
                }
            }
        }
        return null;
    }
}
