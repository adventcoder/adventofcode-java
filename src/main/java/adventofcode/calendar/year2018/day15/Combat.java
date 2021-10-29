package adventofcode.calendar.year2018.day15;

import adventofcode.utils.Vector2D;

import java.util.*;

import static adventofcode.utils.Iterables.sum;

public class Combat {
    public final int goblinAttackPower;
    public final int elfAttackPower;
    private final boolean[][] walls;
    private final Map<Vector2D, Unit> units = new HashMap<>();

    public Combat(String input, int goblinAttackPower, int elfAttackPower) {
        this.goblinAttackPower = goblinAttackPower;
        this.elfAttackPower = elfAttackPower;
        String[] lines = input.split("\n");
        walls = new boolean[lines.length][];
        for (int y = 0; y < lines.length; y++) {
            walls[y] = new boolean[lines[y].length()];
            for (int x = 0; x < walls.length; x++) {
                char c = lines[y].charAt(x);
                if (c == '#') {
                    walls[y][x] = true;
                } else if (c == 'G' || c == 'E') {
                    Unit unit = new Unit(this, new Vector2D(x, y), c);
                    units.put(unit.pos, unit);
                }
            }
        }
    }

    public boolean isOpen(Vector2D pos) {
        return !walls[pos.y][pos.x] && !units.containsKey(pos);
    }

    public boolean hasAttackTargets(char type) {
        for (Unit unit : units.values()) {
            if (unit.type != type) return true;
        }
        return false;
    }

    public int countAttackTargets(char type) {
        int count = 0;
        for (Unit unit : units.values()) {
            if (unit.type != type) count++;
        }
        return count;
    }

    public boolean hasAttackTargets(Vector2D pos, char type) {
        for (Vector2D neighbour : pos.neighbours()) {
            Unit unit = units.get(neighbour);
            if (unit != null && unit.type != type) return true;
        }
        return false;
    }

    public List<Unit> getAttackTargets(Vector2D pos, char type) {
        List<Unit> targets = new ArrayList<>();
        for (Vector2D neighbour : pos.neighbours()) {
            Unit unit = units.get(neighbour);
            if (unit != null && unit.type != type) {
                targets.add(unit);
            }
        }
        return targets;
    }

    public int totalHp() {
        return sum((unit) -> unit.hp, units.values());
    }

    /**
     * Returns true if the round was fully completed, or false otherwise.
     */
    public boolean completeRound() {
        List<Unit> queue = new ArrayList<>(units.values());
        queue.sort((a, b) -> a.pos.compareReadingOrder(b.pos));
        for (Unit unit : queue) {
            if (unit.hp <= 0) continue;
            if (!hasAttackTargets(unit.type)) {
                return false;
            }
            if (!hasAttackTargets(unit.pos, unit.type)) {
                Vector2D newPos = unit.getMove();
                if (newPos != null) {
                    units.remove(unit.pos);
                    unit.pos = newPos;
                    units.put(unit.pos, unit);
                }
            }
            Unit target = unit.getAttackTarget();
            if (target != null) {
                target.hp -= unit.getAttackPower();
                if (target.hp <= 0) {
                    units.remove(target.pos);
                }
            }
        }
        return true;
    }
}
