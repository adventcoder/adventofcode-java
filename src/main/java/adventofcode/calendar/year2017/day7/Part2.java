package adventofcode.calendar.year2017.day7;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Program parent = Program.parseRoot(input);
        parent.computeTotalWeight();
        Program child = parent.findIncorrectChild();
        if (child == null) return null;
        Program nextChild = child.findIncorrectChild();
        while (nextChild != null) {
            parent = child;
            child = nextChild;
            nextChild = nextChild.findIncorrectChild();
        }
        return child.weight + parent.findCorrectTotalWeight() - child.totalWeight;
    }
}
