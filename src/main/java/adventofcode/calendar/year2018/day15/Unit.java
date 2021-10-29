package adventofcode.calendar.year2018.day15;

import adventofcode.utils.Vector2D;

import java.util.*;

import static adventofcode.utils.Iterables.min;

public class Unit {
    private final Combat combat;
    public Vector2D pos;
    public final char type;
    public int hp = 200;

    public Unit(Combat combat, Vector2D pos, char type) {
        this.combat = combat;
        this.pos = pos;
        this.type = type;
    }

    public int getAttackPower() {
        if (type == 'E') return combat.elfAttackPower;
        if (type == 'G') return combat.goblinAttackPower;
        return 3;
    }

    public Unit getAttackTarget() {
        return min(Comparator.comparingInt((unit) -> unit.hp), combat.getAttackTargets(pos, type));
    }

    public Vector2D getMove() {
        List<Vector2D> targets = new ArrayList<>();
        List<Vector2D> frontier = new ArrayList<>();
        Map<Vector2D, Vector2D> startPos = new HashMap<>();
        for (Vector2D newPos : pos.neighbours()) {
            if (combat.isOpen(newPos)) {
                frontier.add(newPos);
                startPos.put(newPos, newPos);
            }
        }
        while (targets.isEmpty() && !frontier.isEmpty()) {
            List<Vector2D> nextFrontier = new ArrayList<>();
            for (Vector2D pos : frontier) {
                if (combat.hasAttackTargets(pos, type)) {
                    targets.add(pos);
                } else {
                    for (Vector2D newPos : pos.neighbours()) {
                        if (combat.isOpen(newPos) && !startPos.containsKey(newPos)) {
                            nextFrontier.add(newPos);
                            startPos.put(newPos, startPos.get(pos));
                        }
                    }
                }
            }
            frontier = nextFrontier;
        }
        if (!targets.isEmpty()) {
            return startPos.get(min(Vector2D::compareReadingOrder, targets));
        }
        return null;
    }
}
