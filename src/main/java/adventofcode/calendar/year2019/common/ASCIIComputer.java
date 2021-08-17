package adventofcode.calendar.year2019.common;

import java.util.ArrayList;
import java.util.List;

public class ASCIIComputer extends IntComputer {
    public ASCIIComputer(String program) {
        super(program);
    }

    public int readChar() {
        return hasNextOutput() ? nextOutput().intValue() : -1;
    }

    public StringBuilder readLine() {
        int c = readChar();
        if (c == -1) {
            return null;
        }
        StringBuilder line = new StringBuilder();
        while (c != '\n') {
            line.append((char) c);
            c = readChar();
            if (c == -1) {
                break;
            }
        }
        return line;
    }

    public List<StringBuilder> readLines() {
        StringBuilder line = readLine();
        if (line == null) {
            return null;
        }
        List<StringBuilder> lines = new ArrayList<>();
        while (line.length() > 0) {
            lines.add(line);
            line = readLine();
            if (line == null) {
                break;
            }
        }
        return lines;
    }
}
