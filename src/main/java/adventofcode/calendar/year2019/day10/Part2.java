package adventofcode.calendar.year2019.day10;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntMath;
import adventofcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part2 extends AbstractPart<Integer> {
    private static final int targetNumber = 200;

    @Override
    public Integer solve(String input) {
        String[] grid = input.split("\n");

        List<Vector2D> stroids = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (grid[y].charAt(x) == '#') {
                    stroids.add(new Vector2D(x, y));
                }
            }
        }

        int[][] shotsRequired = new int[stroids.size()][stroids.size()];
        for (int i = 0; i < stroids.size(); i++) {
            shotsRequired[i][i] = 0;
            for (int j = i + 1; j < stroids.size(); j++) {
                shotsRequired[i][j] = getShotsRequired(stroids.get(i), stroids.get(j), grid);
                shotsRequired[j][i] = shotsRequired[i][j];
            }
        }

        Integer stationIndex = null;
        Integer stationCount = null;
        for (int i = 0; i < stroids.size(); i++) {
            int count = 0;
            for (int j = 0; j < stroids.size(); j++) {
                if (i == j) continue;
                if (shotsRequired[i][j] == 1) count++;
            }
            if (stationCount == null || count > stationCount) {
                stationIndex = i;
                stationCount = count;
            }
        }
        if (stationIndex == null) return null;
        Vector2D stationStroid = stroids.get(stationIndex);
        System.out.println("Station: " + stationStroid);

        List<List<Vector2D>> waves = new ArrayList<>();
        for (int i = 0; i < stroids.size(); i++) {
            if (i == stationIndex) continue;
            int wave = shotsRequired[stationIndex][i] - 1;
            while (wave >= waves.size()) waves.add(new ArrayList<>());
            waves.get(wave).add(stroids.get(i));
        }

        int targetsRemaining = targetNumber;
        int wave = 0;
        while (targetsRemaining > waves.get(wave).size()) {
            System.out.println("vaporized " + waves.get(wave).size() + " stroids in wave " + wave);
            targetsRemaining -= waves.get(wave).size();
            wave++;
        }

        Vector2D laserDir = new Vector2D(0, -1);
        waves.get(wave).sort(Comparator.comparingDouble((stroid) -> laserDir.angleTo(stroid.sub(stationStroid))));

        // for (int n = 1; n <= targetsRemaining; n++) {
        //     System.out.println("The #" + n + " asteroid to be vaporized is at " + waves.get(wave).get(n - 1));
        // }
        Vector2D target = waves.get(wave).get(targetsRemaining - 1);
        return target.x * 100 + target.y;
    }

    private int getShotsRequired(Vector2D a, Vector2D b, String[] grid) {
        int count = 0;
        Vector2D step = b.sub(a);
        step.divEq(IntMath.gcd(step.x, step.y));
        for (Vector2D p = new Vector2D(a); !p.equals(b); p.addEq(step)) {
            if (grid[p.y].charAt(p.x) == '#') {
                count++;
            }
        }
        return count;
    }
}
