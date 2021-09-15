package adventofcode.calendar.year2019.day25;

import adventofcode.calendar.year2019.common.ASCIIComputer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Droid {
    private final ASCIIComputer computer;
    public String room;
    public String roomDescription;
    public List<String> itemsHere;
    public List<Dir> doorsHereLead;

    public Droid(ASCIIComputer computer) {
        this.computer = computer;
        StringBuilder prompt = readRoom();
        if (prompt == null || !prompt.toString().equals("Command?")) {
            throw new InputMismatchException();
        }
    }

    public boolean move(Dir dir) {
        computer.writeLine(dir.toString());
        StringBuilder prompt = readRoom();
        if (prompt != null && !prompt.toString().equals("Command?")) {
            throw new InputMismatchException();
        }
        return prompt != null;
    }

    public String moveFinal(Dir dir) {
        computer.writeLine(dir.toString());
        StringBuilder prompt = readRoom();
        if (prompt == null || prompt.toString().equals("Command?")) {
            throw new InputMismatchException();
        }
        if (prompt.toString().contains("Analysis complete! You may proceed.")) {
            computer.readLine();
            Matcher matcher = Pattern.compile("\\d+").matcher(computer.readLine());
            if (!matcher.find()) throw new InputMismatchException();
            return matcher.group();
        } else {
            prompt = readRoom();
            if (prompt == null || !prompt.toString().equals("Command?")) {
                throw new InputMismatchException();
            }
            return null;
        }
    }

    public void take(String item) {
        computer.writeLine("take " + item);
        StringBuilder line = computer.readLine();
        while (line != null && !line.toString().equals("Command?")) {
            if (line.toString().equals("You take the " + item + ".")) {
                itemsHere.remove(item);
            }
            line = computer.readLine();
        }
    }

    public void drop(String item) {
        computer.writeLine("drop " + item);
        StringBuilder line = computer.readLine();
        while (!line.toString().equals("Command?")) {
            if (line.toString().equals("You drop the " + item + ".")) {
                itemsHere.add(item);
            }
            line = computer.readLine();
        }
    }

    public List<String> inv() {
        computer.writeLine("inv");
        List<String> items = new ArrayList<>();
        StringBuilder line = computer.readLine();
        while (!line.toString().equals("Command?")) {
            if (line.toString().equals("Items in your inventory:")) {
                line = computer.readLine();
                while (Pattern.matches("- .*", line)) {
                    items.add(line.substring(2));
                    line = computer.readLine();
                }
            }
            line = computer.readLine();
        }
        return items;
    }

    private StringBuilder readRoom() {
        StringBuilder line = computer.readLine();
        while (!Pattern.matches("== .* ==", line)) {
            if (line.toString().equals("Command?")) {
                return null;
            }
            line = computer.readLine();
        }
        room = line.substring(3, line.length() - 3);
        roomDescription = computer.readLine().toString();
        itemsHere = new ArrayList<>();
        doorsHereLead = new ArrayList<>();
        line = computer.readLine();
        while (!line.toString().equals("Command?") && !line.toString().startsWith("A loud, robotic voice says ")) {
            if (line.toString().equals("Items here:")) {
                line = computer.readLine();
                while (Pattern.matches("- .*", line)) {
                    itemsHere.add(line.substring(2));
                    line = computer.readLine();
                }
            } else if (line.toString().equals("Doors here lead:")) {
                line = computer.readLine();
                while (Pattern.matches("- .*", line)) {
                    doorsHereLead.add(Dir.parseDir(line.substring(2)));
                    line = computer.readLine();
                }
            }
            line = computer.readLine();
        }
        return line;
    }
}
