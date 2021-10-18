package adventofcode.calendar.year2019.day10;

import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FieldView {
    private Field field;
    private int origin;

    public FieldView(Field field, int origin) {
        this.field = field;
        this.origin = origin;
    }

    public int countVisible() {
        return field.countVisible(origin);
    }

    public Vector2D calculateTargetPosition(int targetNumber, Vector2D laserDir) {
        List<List<Integer>> waves = new ArrayList<>();
        for (int i = 0; i < field.size(); i++) {
            if (origin == i) continue;
            int waveIndex = field.distance(origin, i) - 1;
            while (waveIndex >= waves.size()) {
                waves.add(new ArrayList<>());
            }
            waves.get(waveIndex).add(i);
        }
        for (List<Integer> wave : waves) {
            if (targetNumber <= wave.size()) {
                // Could do quick select here to avoid doing a complete sort but it doesn't really matter.
                wave.sort(Comparator.comparingDouble((i) -> laserDir.angleTo(field.direction(origin, i))));
                return field.getPosition(wave.get(targetNumber - 1));
            } else {
                targetNumber -= wave.size();
            }
        }
        return null;
    }
}
