package adventofcode.calendar.year2019.day15;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.search.DFS;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Droid droid = new Droid(input);
        DFS<Droid, Dir> search = new DFS<>(droid);
        while (droid.status != 2) {
            if (!search.advance()) {
                return null;
            }
        }
        search.clear();
        int maxDistance = 0;
        while (search.advance()) {
            maxDistance = Math.max(maxDistance, search.depth());
        }
        return maxDistance;
    }
}
