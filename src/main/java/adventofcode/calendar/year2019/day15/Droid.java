package adventofcode.calendar.year2019.day15;

import adventofcode.calendar.year2019.common.IntComputer;
import adventofcode.utils.search.Traversable;

import java.util.Arrays;

public class Droid extends IntComputer implements Traversable<Dir> {
    public int status = 0;

    public Droid(String program) {
        super(program);
    }

    @Override
    public Iterable<Dir> actions() {
        return Arrays.asList(Dir.values());
    }

    @Override
    public boolean tryApply(Dir dir) {
        acceptInput(dir.value());
        status = nextOutput().intValue();
        return status != 0;
    }
}
