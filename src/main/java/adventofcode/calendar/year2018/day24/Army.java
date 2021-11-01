package adventofcode.calendar.year2018.day24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static adventofcode.utils.Iterables.*;

public class Army {
    public final List<Group> groups = new ArrayList<>();

    public static Army parse(String input, String name) {
        for (String chunk : input.split("\n\n")) {
            String[] lines = chunk.split("\n");
            if (lines[0].equals(name + ":")) {
                return new Army(lines);
            }
        }
        return null;
    }

    public Army(String[] lines) {
        for (int i = 1; i < lines.length; i++) {
            groups.add(new Group(lines[i]));
        }
    }

    public void reset() {
        groups.forEach(Group::reset);
    }

    public boolean isDead() {
        return all(Group::isDead, groups);
    }

    public int units() {
        return sum((group) -> group.units, groups);
    }

    public void fight(Army other) {
        chooseTargets(other);
        other.chooseTargets(this);
        attack(other);
    }

    public void chooseTargets(Army other) {
        groups.sort(Group::compareEffectiveness);
        Collections.reverse(groups);
        for (Group group : groups) {
            if (group.isDead()) continue;
            group.chooseTarget(other);
        }
    }

    public void attack(Army other) {
        List<Group> attackers = new ArrayList<>();
        attackers.addAll(this.groups);
        attackers.addAll(other.groups);
        attackers.sort(Comparator.comparing((group) -> group.initiative));
        Collections.reverse(attackers);
        for (Group group : attackers) {
            // It's ok for dead groups to still attack.
            // They'll have no units and so do no damage.
            group.attack();
        }
    }
}
