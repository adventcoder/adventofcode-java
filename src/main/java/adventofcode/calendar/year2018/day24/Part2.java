package adventofcode.calendar.year2018.day24;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Army immuneSystem = Army.parse(input, "Immune System");
        Army infection = Army.parse(input, "Infection");
        while (true) {
            int units = immuneSystem.units() + infection.units();
            while (!immuneSystem.isDead() || !infection.isDead()) {
                int oldUnits = units;
                immuneSystem.fight(infection);
                units = immuneSystem.units() + infection.units();
                if (units == oldUnits) {
                    break;
                }
            }
            if (infection.isDead()) {
                return units;
            }
            immuneSystem.reset();
            infection.reset();
            for (Group group : immuneSystem.groups) {
                group.attackDamage++;
            }
        }
    }
}
