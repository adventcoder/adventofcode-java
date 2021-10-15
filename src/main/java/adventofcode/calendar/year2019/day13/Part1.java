package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int blockCount = 0;
        BufferedIntcode game = new BufferedIntcode(input);
        while (game.hasNext()) {
            game.next();
            game.next();
            if (game.next().intValueExact() == 2) {
                blockCount++;
            }
        }
        return blockCount;
    }
}
