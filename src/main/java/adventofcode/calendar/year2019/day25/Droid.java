package adventofcode.calendar.year2019.day25;

import adventofcode.calendar.year2019.BufferedIntcode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Droid {
    private final BufferedIntcode code;
    public String room;
    public String roomDescription;
    public List<String> itemsHere;
    public List<Dir> doorsHereLead;

    public Droid(String program) {
        code = new BufferedIntcode(program);
        String prompt = readRoom();
        if (prompt == null || !prompt.equals("Command?")) {
            throw new InputMismatchException();
        }
    }

    public void takeAllItems(Collection<String> skipItems, Dir skipDir) {
        for (String item : new ArrayList<>(itemsHere)) {
            if (!skipItems.contains(item)) {
                take(item);
            }
        }
        if (!room.equals("Security Checkpoint")) {
            for (Dir dir : doorsHereLead) {
                if (dir == skipDir) continue;
                if (move(dir)) {
                    takeAllItems(skipItems, dir.opposite());
                    if (!move(dir.opposite())) {
                        throw new IllegalStateException("stuck!");
                    }
                }
            }
        }
    }

    public String trialAndError(Dir skipDir) {
        if (room.equals("Security Checkpoint")) {
            List<String> items = inv();
            int mask = (1 << items.size()) - 1;
            for (int i = 0; i < (1 << items.size()); i++) {
                for (Dir dir : doorsHereLead) {
                    if (dir == skipDir) continue;
                    String password = moveFinal(dir);
                    if (password != null) return password;
                }
                int flipIndex = Integer.numberOfTrailingZeros(~i);
                if ((mask & (1 << flipIndex)) == 0) {
                    take(items.get(flipIndex));
                } else {
                    drop(items.get(flipIndex));
                }
                mask ^= 1 << flipIndex;
            }
        } else {
            for (Dir dir : doorsHereLead) {
                if (dir == skipDir) continue;
                if (move(dir)) {
                    String password = trialAndError(dir.opposite());
                    if (password != null) return password;
                    if (!move(dir.opposite())) {
                        throw new IllegalStateException("stuck!");
                    }
                }
            }
        }
        return null;
    }

    public boolean move(Dir dir) {
        code.writeLine(dir.toString());
        String prompt = readRoom();
        if (prompt != null && !prompt.equals("Command?")) {
            throw new InputMismatchException();
        }
        return prompt != null;
    }

    public String moveFinal(Dir dir) {
        code.writeLine(dir.toString());
        String prompt = readRoom();
        if (prompt == null || prompt.equals("Command?")) {
            throw new InputMismatchException();
        }
        if (prompt.contains("Analysis complete! You may proceed.")) {
            code.readLine();
            Matcher matcher = Pattern.compile("\\d+").matcher(code.readLine());
            if (!matcher.find()) throw new InputMismatchException();
            return matcher.group();
        } else {
            prompt = readRoom();
            if (prompt == null || !prompt.equals("Command?")) {
                throw new InputMismatchException();
            }
            return null;
        }
    }

    public void take(String item) {
        code.writeLine("take " + item);
        String line = code.readLine();
        while (line != null && !line.equals("Command?")) {
            if (line.equals("You take the " + item + ".")) {
                itemsHere.remove(item);
            }
            line = code.readLine();
        }
    }

    public void drop(String item) {
        code.writeLine("drop " + item);
        String line = code.readLine();
        while (!line.equals("Command?")) {
            if (line.equals("You drop the " + item + ".")) {
                itemsHere.add(item);
            }
            line = code.readLine();
        }
    }

    public List<String> inv() {
        code.writeLine("inv");
        List<String> items = new ArrayList<>();
        String line = code.readLine();
        while (!line.equals("Command?")) {
            if (line.equals("Items in your inventory:")) {
                line = code.readLine();
                while (line.matches("- .*")) {
                    items.add(line.substring(2));
                    line = code.readLine();
                }
            }
            line = code.readLine();
        }
        return items;
    }

    private String readRoom() {
        String line = code.readLine();
        while (!line.matches("== .* ==")) {
            if (line.equals("Command?")) {
                return null;
            }
            line = code.readLine();
        }
        room = line.substring(3, line.length() - 3);
        roomDescription = code.readLine();
        itemsHere = new ArrayList<>();
        doorsHereLead = new ArrayList<>();
        line = code.readLine();
        while (!line.equals("Command?") && !line.startsWith("A loud, robotic voice says ")) {
            if (line.equals("Items here:")) {
                line = code.readLine();
                while (line.matches("- .*")) {
                    itemsHere.add(line.substring(2));
                    line = code.readLine();
                }
            } else if (line.equals("Doors here lead:")) {
                line = code.readLine();
                while (line.matches("- .*")) {
                    doorsHereLead.add(Dir.parseDir(line.substring(2)));
                    line = code.readLine();
                }
            }
            line = code.readLine();
        }
        return line;
    }
}
