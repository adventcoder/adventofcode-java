package adventofcode.calendar.year2019.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ASCIIComputer extends IntComputer {
    public ASCIIComputer(String program) {
        super(program);
    }

    public void write(int codePoint) {
        acceptInput(BigInteger.valueOf(codePoint & 0x7F));
    }

    public void write(String string) {
        int i = 0;
        while (i < string.length()) {
            int codePoint = string.codePointAt(i);
            write(codePoint);
            i += Character.charCount(codePoint);
        }
    }

    public void writeLine(String line) {
        write(line);
        write('\n');
    }

    public int read() {
        if (hasNextOutput()) {
            int codePoint = nextOutput().intValueExact();
            if (codePoint < 0 || codePoint >= 0x80) {
                throw new InputMismatchException("not an ASCII code point: " + codePoint);
            }
            return codePoint;
        }
        return -1;
    }

    public StringBuilder readLine() {
        int c = read();
        if (c == -1) {
            return null;
        }
        StringBuilder line = new StringBuilder();
        while (c != '\n') {
            line.append((char) c);
            c = read();
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
