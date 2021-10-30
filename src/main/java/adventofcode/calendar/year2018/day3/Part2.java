package adventofcode.calendar.year2018.day3;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.Iterables;

import java.util.*;

import static adventofcode.utils.Iterables.findFirst;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        Set<Integer> overlappingIds = new HashSet<>();
        List<Integer> allIds = new ArrayList<>();
        Claim[][] claims = new Claim[1000][1000];
        for (String line : input.split("\n")) {
            Claim claim = new Claim(line);
            allIds.add(claim.id);
            for (int y = 0; y < claim.height; y++) {
                for (int x = 0; x < claim.width; x++) {
                    Claim prev = claims[y + claim.y][x + claim.x];
                    claims[y + claim.y][x + claim.x] = claim;
                    if (prev != null) {
                        overlappingIds.add(prev.id);
                        overlappingIds.add(claim.id);
                    }
                }
            }
        }
        return findFirst((id) -> !overlappingIds.contains(id), allIds);
    }
}
