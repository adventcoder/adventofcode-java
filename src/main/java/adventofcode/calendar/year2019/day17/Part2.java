package adventofcode.calendar.year2019.day17;

import adventofcode.calendar.year2019.common.ASCIIComputer;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.geometry.Vec2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        ASCIIComputer terminal = new ASCIIComputer(input);
        terminal.set(0, BigInteger.TWO);
        List<StringBuilder> grid = terminal.readLines();
        List<String> main = new ArrayList<>();
        Map<String, List<String>> subs = new HashMap<>();
        if (!tryMakeRoutines(findPath(grid), main, subs)) {
            return null;
        }
        terminal.readLine(); // Main
        terminal.writeLine(String.join(",", main));
        terminal.readLine(); // Function A
        terminal.writeLine(String.join(",", subs.get("A")));
        terminal.readLine(); // Function B
        terminal.writeLine(String.join(",", subs.get("B")));
        terminal.readLine(); // Function C
        terminal.writeLine(String.join(",", subs.get("C")));
        terminal.readLine(); // Continuous video feed?
        terminal.writeLine("n");
        while (terminal.peekOutput().compareTo(BigInteger.valueOf(0x80)) >= 0) {
            System.out.println(terminal.readLine());
            for (StringBuilder line : terminal.readLines()) {
                System.out.println(line);
            }
        }
        return terminal.nextOutput();
    }

    public boolean tryMakeRoutines(List<String> path, List<String> main, Map<String, List<String>> subs) {
        if (String.join(",", main).length() > 20) {
            return false;
        }
        if (path.isEmpty()) {
            return true;
        }
        // Try using a new subroutine
        if (subs.size() < 3) {
            String name = Character.toString((char) ('A' + subs.size()));
            for (int size = 2; String.join(",", path.subList(0, size)).length() < 20; size += 2) {
                subs.put(name, path.subList(0, size));
                main.add(name);
                if (tryMakeRoutines(path.subList(size, path.size()), main, subs)) {
                    return true;
                }
                main.remove(main.size() - 1);
                subs.remove(name);
            }
        }
        // Try reusing an existing subroutine
        for (String name : subs.keySet()) {
            List<String> sub = subs.get(name);
            if (sub.size() <= path.size() && path.subList(0, sub.size()).equals(sub)) {
                main.add(name);
                if (tryMakeRoutines(path.subList(sub.size(), path.size()), main, subs)) {
                    return true;
                }
                main.remove(main.size() - 1);
            }
        }
        return false;
    }

    public List<String> findPath(List<StringBuilder> grid) {
        List<String> route = new ArrayList<>();
        Vec2 pos = findInitialPosition(grid);
        Vec2 dir = new Vec2(0, -1);
        while (true) {
            if (isScaffold(grid, pos.x - dir.y, pos.y + dir.x)) {
                dir.rotateRight();
                route.add("R");
            } else if (isScaffold(grid, pos.x + dir.y, pos.y - dir.x)) {
                dir.rotateLeft();
                route.add("L");
            } else {
                break;
            }
            int distance = 0;
            do {
                pos.add(dir);
                distance++;
            } while (isScaffold(grid, pos.x + dir.x, pos.y + dir.y));
            route.add(Integer.toString(distance));
        }
        return route;
    }

    private boolean isScaffold(List<StringBuilder> grid, int x, int y) {
        return y >= 0 && y < grid.size() && x >= 0 && x < grid.get(y).length() && grid.get(y).charAt(x) == '#';
    }

    private Vec2 findInitialPosition(List<StringBuilder> grid) {
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(y).length(); x++) {
                if (grid.get(y).charAt(x) == '^') {
                    return new Vec2(x, y);
                }
            }
        }
        return null;
    }
}
