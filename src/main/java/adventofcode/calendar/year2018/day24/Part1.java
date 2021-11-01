package adventofcode.calendar.year2018.day24;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Army immuneSystem = Army.parse(input, "Immune System");
        Army infection = Army.parse(input, "Infection");
        while (!immuneSystem.isDead()) {
            immuneSystem.fight(infection);
        }
        return infection.units();
    }
}
