package adventofcode.calendar.year2019.day17;

import adventofcode.calendar.year2019.common.ASCIIComputer;
import adventofcode.framework.AbstractPart;

import java.util.List;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        int sum = 0;
        ASCIIComputer terminal = new ASCIIComputer(input);
        List<StringBuilder> lines = terminal.readLines();
        for (int y = 1; y < lines.size() - 1; y++) {
            for (int x = 1; x < lines.get(y).length() - 1; x++) {
                if (isIntersection(lines, x, y)) {
                    sum += x * y;
                }
            }
        }
        return sum;
    }

    private boolean isIntersection(List<StringBuilder> lines, int x, int y) {
        return lines.get(y).charAt(x) != '#' &&
                lines.get(y - 1).charAt(x) != '#' &&
                lines.get(y + 1).charAt(x) != '#' &&
                lines.get(y).charAt(x - 1) != '#' &&
                lines.get(y).charAt(x + 1) != '#';
    }
}
