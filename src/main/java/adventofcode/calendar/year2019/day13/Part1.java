package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int blockCount = 0;
        IntComputer game = new IntComputer(input);
        while (game.hasNextOutput()) {
            int x = game.nextOutput().intValue();
            int y = game.nextOutput().intValue();
            int tileId = game.nextOutput().intValue();
            if (tileId == 2) {
                blockCount++;
            }
        }
        return blockCount;
    }
}
