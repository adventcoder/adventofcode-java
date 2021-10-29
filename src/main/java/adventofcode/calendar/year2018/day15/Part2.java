package adventofcode.calendar.year2018.day15;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Combat combat = new Combat(input);
        int elfCount = combat.countUnits('E');
        for (int attackPower = 4; ; attackPower++) {
            Combat trial = combat.withElfAttackPower(attackPower);
            int rounds = 0;
            while (trial.countUnits('E') == elfCount) {
                if (!trial.completeRound()) {
                    return rounds * trial.totalHp();
                }
                rounds++;
            }
        }
    }
}
