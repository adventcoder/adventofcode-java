package adventofcode.calendar.year2019.day13;

import adventofcode.calendar.year2019.common.IntComputer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame extends IntComputer {
    private final List<BigInteger> prevOutputs = new ArrayList<>(2);

    public AbstractGame(String program) {
        super(program);
    }

    @Override
    public void put(BigInteger output) {
        if (prevOutputs.size() < 2) {
            prevOutputs.add(output);
        } else {
            putTile(prevOutputs.get(0).intValue(), prevOutputs.get(1).intValue(), output.intValue());
            prevOutputs.clear();
        }
    }

    public abstract void putTile(int tileX, int tileY, int tileId);
}
