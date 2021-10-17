package adventofcode.calendar.year2019.day17;

import adventofcode.calendar.year2019.BufferedIntcode;
import adventofcode.framework.AbstractPart;
import adventofcode.utils.Vector2D;

import java.math.BigInteger;
import java.util.*;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BufferedIntcode terminal = new BufferedIntcode(input);
        terminal.set(0, BigInteger.TWO);
        List<String> grid = terminal.readLines();
        List<String> path = buildPath(grid);
        List<String> main = new ArrayList<>();
        Map<String, List<String>> subs = new HashMap<>();
        if (!tryMakeRoutines(path, main, subs)) return null;
        terminal.writeLine(String.join(",", main));
        terminal.writeLine(String.join(",", subs.get("A")));
        terminal.writeLine(String.join(",", subs.get("B")));
        terminal.writeLine(String.join(",", subs.get("C")));
        terminal.writeLine("n");
        while (terminal.hasNextCodePoint()) {
            terminal.next();
        }
        return terminal.next();
    }

    //TODO: clean this up?
    public boolean tryMakeRoutines(List<String> path, List<String> main, Map<String, List<String>> subs) {
        if (String.join(",", main).length() > 20) {
            return false;
        }
        if (path.isEmpty()) {
            return true;
        }
        // Try using an existing subroutine
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
        return false;
    }

    public List<String> buildPath(List<String> grid) {
        List<String> route = new ArrayList<>();
        Vector2D pos = getInitialPosition(grid);
        Vector2D dir = new Vector2D(0, -1);
        while (true) {
            String turn = getTurn(grid, pos, dir);
            if (turn == null) return route;
            route.add(turn);
            int distance = 0;
            do {
                pos.addEq(dir);
                distance++;
            } while (isScaffold(grid, pos.add(dir)));
            route.add(Integer.toString(distance));
        }
    }

    private String getTurn(List<String> grid, Vector2D pos, Vector2D dir) {
        dir.rotateRight();
        if (isScaffold(grid, pos.add(dir))) {
            return "R";
        }
        dir.negate();
        if (isScaffold(grid, pos.add(dir))) {
            return "L";
        }
        return null;
    }

    private boolean isScaffold(List<String> grid, Vector2D pos) {
        return pos.y >= 0 && pos.y < grid.size() &&
                pos.x >= 0 && pos.x < grid.get(pos.y).length() &&
                grid.get(pos.y).charAt(pos.x) == '#';
    }

    private Vector2D getInitialPosition(List<String> grid) {
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(y).length(); x++) {
                if (grid.get(y).charAt(x) == '^') {
                    return new Vector2D(x, y);
                }
            }
        }
        throw new NoSuchElementException();
    }
}
