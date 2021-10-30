package adventofcode.calendar.year2018.day17;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Section section = new Section(input);
        section.fill(500 - section.x, 0);
        // System.out.println(section);
        return section.count('|') + section.count('~');
    }
}
