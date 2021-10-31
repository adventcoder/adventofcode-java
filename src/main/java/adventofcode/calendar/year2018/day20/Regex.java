package adventofcode.calendar.year2018.day20;

import adventofcode.utils.Vector2D;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public abstract class Regex {
    private static Map<Character, Vector2D> dirs = Map.ofEntries(
            Map.entry('N', new Vector2D(0, -1)),
            Map.entry('S', new Vector2D(0, 1)),
            Map.entry('W', new Vector2D(-1, 0)),
            Map.entry('E', new Vector2D(1, 0)));

    private static boolean tryMatch(CharacterIterator it, char c) {
        if (it.current() == c) {
            it.next();
            return true;
        }
        return false;
    }

    private static void match(CharacterIterator it, char c) {
        if (!tryMatch(it, c)) {
            throw new InputMismatchException(Character.toString(it.current()));
        }
    }

    public static Regex compile(String input) {
        CharacterIterator it = new StringCharacterIterator(input);
        match(it, '^');
        Regex regex = new Seq(it);
        match(it, '$');
        return regex;
    }

    public abstract Set<Vector2D> addDoors(Set<Vector2D> rooms, Maze maze);

    public static class Seq extends Regex {
        private final List<Term> terms = new ArrayList<>();

        public Seq(CharacterIterator it) {
            while (it.current() == '(' || dirs.containsKey(it.current())) {
                terms.add(new Term(it));
            }
        }

        @Override
        public Set<Vector2D> addDoors(Set<Vector2D> rooms, Maze maze) {
            for (Term term : terms) {
                rooms = term.addDoors(rooms, maze);
            }
            return rooms;
        }
    }

    public static class Term extends Regex {
        private final List<Seq> opts;
        private final Vector2D dir;

        public Term(CharacterIterator it) {
            if (tryMatch(it, '(')) {
                dir = null;
                opts = new ArrayList<>();
                opts.add(new Seq(it));
                while (tryMatch(it, '|')) {
                    opts.add(new Seq(it));
                }
                match(it, ')');
            } else if (dirs.containsKey(it.current())) {
                opts = null;
                dir = dirs.get(it.current());
                it.next();
            } else {
                throw new InputMismatchException();
            }
        }

        @Override
        public Set<Vector2D> addDoors(Set<Vector2D> rooms, Maze maze) {
            Set<Vector2D> newRooms = new HashSet<>();
            if (opts != null) {
                for (Seq opt : opts) {
                    newRooms.addAll(opt.addDoors(rooms, maze));
                }
            } else {
                for (Vector2D room : rooms) {
                    Vector2D newRoom = room.add(dir);
                    maze.addDoor(room, newRoom);
                    newRooms.add(newRoom);
                }
            }
            return newRooms;
        }
    }
}
