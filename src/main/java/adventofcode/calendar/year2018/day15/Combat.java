package adventofcode.calendar.year2018.day15;

import adventofcode.utils.Vector2D;

import java.util.*;

import static adventofcode.utils.Iterables.*;

public class Combat {
    private final boolean[][] walls;
    private final Map<Vector2D, Unit> units = new HashMap<>();

    public Combat(String input) {
        String[] lines = input.split("\n");
        walls = new boolean[lines.length][];
        for (int y = 0; y < lines.length; y++) {
            walls[y] = new boolean[lines[y].length()];
            for (int x = 0; x < walls.length; x++) {
                char c = lines[y].charAt(x);
                if (c == '#') {
                    walls[y][x] = true;
                } else if (c != '.') {
                    Vector2D pos = new Vector2D(x, y);
                    units.put(pos, new Unit(this, pos, c, 200));
                }
            }
        }
    }

    public Combat(Combat other) {
        walls = new boolean[other.walls.length][];
        for (int y = 0; y < walls.length; y++) {
            walls[y] = other.walls[y].clone();
        }
        for (Unit otherUnit : other.units.values()) {
            units.put(otherUnit.pos, new Unit(this, otherUnit.pos, otherUnit.type, otherUnit.hp));
        }
    }

    public Combat withElfAttackPower(int elfAttackPower) {
        return new Combat(this) {
            @Override
            public int getAttackPower(int type) {
                if (type == 'E') return elfAttackPower;
                return super.getAttackPower(type);
            }
        };
    }

    public int getAttackPower(int type) {
        return 3;
    }

    public boolean isWall(Vector2D pos) {
        return walls[pos.y][pos.x];
    }

    public Unit getUnit(Vector2D pos) {
        return units.get(pos);
    }

    public int totalHp() {
        return sum((unit) -> unit.hp, units.values());
    }

    public int countUnits(char type) {
        return tally((unit) -> unit.type == type, units.values());
    }

    public boolean hasTargets(char type) {
        return any((unit) -> unit.type != type, units.values());
    }

    public boolean completeRound() {
        List<Unit> order = new ArrayList<>(units.values());
        order.sort(Comparator.comparing((unit) -> unit.pos, Vector2D::compareReadingOrder));
        for (Unit unit : order) {
            if (unit.hp <= 0) continue;
            if (!hasTargets(unit.type)) {
                return false;
            }
            Unit target = unit.getAttackTarget();
            if (target == null) {
                Vector2D newPos = unit.getMove();
                if (newPos != null) {
                    units.remove(unit.pos);
                    unit.pos = newPos;
                    units.put(newPos, unit);
                    target = unit.getAttackTarget();
                }
            }
            if (target != null) {
                target.hp -= getAttackPower(unit.type);
                if (target.hp <= 0) {
                    units.remove(target.pos);
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder[] lines = new StringBuilder[walls.length];
        for (int y = 0; y < walls.length; y++) {
            lines[y] = new StringBuilder(walls[y].length);
            List<String> hps = new ArrayList<>();
            for (int x = 0; x < walls[y].length; x++) {
                Vector2D pos = new Vector2D(x, y);
                if (units.containsKey(pos)) {
                    hps.add(units.get(pos).type + "(" + units.get(pos).hp + ")");
                    lines[y].append(units.get(pos).type);
                } else {
                    lines[y].append(walls[y][x] ? '#' : '.');
                }
            }
            if (!hps.isEmpty()) {
                lines[y].append("   ").append(String.join(", ", hps));
            }
        }
        return String.join("\n", lines);
    }
}
