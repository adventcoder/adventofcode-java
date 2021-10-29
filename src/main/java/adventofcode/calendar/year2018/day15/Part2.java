package adventofcode.calendar.year2018.day15;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        for (int atk = 4; ; atk++) {
            Combat combat = new Combat(input, 3, atk);
            int elfCount = combat.countAttackTargets('G');
            int rounds = 0;
            while (combat.completeRound()) {
                rounds++;
            }
            if (combat.countAttackTargets('G') == elfCount) {
                System.out.println("Attack: " + atk);
                System.out.println("Rounds: " + rounds);
                return rounds * combat.totalHp();
            }
        }
    }
}
