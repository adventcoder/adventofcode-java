package adventofcode.calendar.year2018.day15;

import adventofcode.utils.Vector2D;

import java.util.*;

import static adventofcode.utils.Iterables.min;

public class Unit {
    private final Combat combat;
    public Vector2D pos;
    public final char type;
    public int hp;

    public Unit(Combat combat, Vector2D pos, char type, int hp) {
        this.combat = combat;
        this.pos = pos;
        this.type = type;
        this.hp = hp;
    }

    public Vector2D getMove() {
        //TODO: could probably speed this up by remember the previous path or something...
        List<Vector2D> targets = new ArrayList<>();
        List<Vector2D> frontier = new ArrayList<>();
        Map<Vector2D, Vector2D> startPos = new HashMap<>();
        for (Vector2D newPos : pos.neighbours()) {
            if (!combat.isWall(newPos) && combat.getUnit(newPos) == null) {
                frontier.add(newPos);
                startPos.put(newPos, newPos);
            }
        }
        while (targets.isEmpty() && !frontier.isEmpty()) {
            List<Vector2D> nextFrontier = new ArrayList<>();
            for (Vector2D pos : frontier) {
                if (getAttackTarget(pos) != null) {
                    targets.add(pos);
                } else {
                    for (Vector2D newPos : pos.neighbours()) {
                        if (!combat.isWall(newPos) && combat.getUnit(newPos) == null && !startPos.containsKey(newPos)) {
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

    public Unit getAttackTarget() {
        return getAttackTarget(pos);
    }

    private Unit getAttackTarget(Vector2D pos) {
        Unit target = null;
        for (Vector2D neighbour : pos.neighbours()) {
            Unit unit = combat.getUnit(neighbour);
            if (unit == null || unit.type == type) continue;
            if (target == null || unit.hp < target.hp) {
                target = unit;
            }
        }
        return target;
    }
}
