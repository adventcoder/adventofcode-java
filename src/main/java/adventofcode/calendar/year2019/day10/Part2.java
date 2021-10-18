package adventofcode.calendar.year2019.day10;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import static adventofcode.utils.Iterables.argMax;

public class Part2 extends AbstractPart<Integer> {
    private static final int targetNumber = 200;
    private static final Vector2D laserDir = new Vector2D(0, -1);

    @Override
    public Integer solve(String input) {
        FieldView stationView = argMax(FieldView::countVisible, new Field(input).views());
        if (stationView != null) {
            Vector2D targetPos = stationView.calculateTargetPosition(targetNumber, laserDir);
            if (targetPos != null) {
                return targetPos.x * 100 + targetPos.y;
            }
        }
        return null;
    }
}
