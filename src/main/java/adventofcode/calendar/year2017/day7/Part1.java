package adventofcode.calendar.year2017.day7;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        return Program.parseRoot(input).name;
    }
}
