package adventofcode.calendar.year2018.day15;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Combat combat = new Combat(input, 3, 3);
        int rounds = 0;
        while (combat.completeRound()) {
            rounds++;
        }
        return rounds * combat.totalHp();
    }
}
