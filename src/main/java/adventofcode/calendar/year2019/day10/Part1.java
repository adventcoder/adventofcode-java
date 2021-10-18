package adventofcode.calendar.year2019.day10;

import adventofcode.framework.AbstractPart;

import static adventofcode.utils.Iterables.max;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        return max(FieldView::countVisible, new Field(input).views());
    }
}
