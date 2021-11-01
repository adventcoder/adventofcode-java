package adventofcode.calendar.year2018.day24;

import java.util.*;

public class Group {
    public final int initialUnits;
    public final int hitPoints;
    public int attackDamage;
    public final String attackType;
    public final int initiative;
    public final Set<String> weaknesses = new HashSet<>();
    public final Set<String> immunities = new HashSet<>();
    public int units;
    public Group target;
    public Group targetedBy;

    public Group(String line) {
        int i = line.indexOf('(');
        if (i >= 0) {
            int j = line.indexOf(')', i + 1);
            parseModifiers(line.substring(i + 1, j));
            line = line.substring(0, i) + line.substring(j + 1);
        }
        String[] words = line.split("\\s+");
        units = initialUnits = Integer.parseInt(words[0]);
        hitPoints = Integer.parseInt(words[4]);
        attackDamage = Integer.parseInt(words[12]);
        attackType = words[13];
        initiative = Integer.parseInt(words[17]);
    }

    private void parseModifiers(String str) {
        for (String part : str.split(";\\s*")) {
            String[] words = part.replace(",", "").split("\\s+");
            if (words[0].equals("weak")) {
                weaknesses.addAll(Arrays.asList(words).subList(2, words.length));
            } else if (words[0].equals("immune")) {
                immunities.addAll(Arrays.asList(words).subList(2, words.length));
            }
        }
    }

    public void reset() {
        units = initialUnits;
    }

    public boolean isDead() {
        return units == 0;
    }

    public int effectivePower() {
        return units * attackDamage;
    }

    public int calculateDamage(Group target) {
        if (target.immunities.contains(attackType)) {
            return 0;
        } else if (target.weaknesses.contains(attackType)) {
            return effectivePower() * 2;
        } else {
            return effectivePower();
        }
    }

    public void receiveDamage(int damage) {
        units = Math.max(units - damage / hitPoints, 0);
    }

    public int compareEffectiveness(Group other) {
        int cmp = Integer.compare(effectivePower(), other.effectivePower());
        if (cmp == 0) {
            cmp = Integer.compare(initiative, other.initiative);
        }
        return cmp;
    }

    public void chooseTarget(Army army) {
        if (target == null) {
            Group bestTarget = null;
            int bestDamage = 0;
            for (Group group : army.groups) {
                if (group.isDead() || group.targetedBy != null) continue;
                int damage = calculateDamage(group);
                if (damage == 0) continue;
                if (bestTarget == null || damage > bestDamage || (damage == bestDamage && group.compareEffectiveness(bestTarget) > 0)) {
                    bestTarget = group;
                    bestDamage = damage;
                }
            }
            if (bestTarget != null) {
                target = bestTarget;
                target.targetedBy = this;
            }
        }
    }

    public void attack() {
        if (target != null) {
            target.receiveDamage(calculateDamage(target));
            target.targetedBy = null;
            target = null;
        }
    }
}
