package adventofcode.calendar.year2019.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ASCIIComputer extends IntComputer {
    public InputStream in = System.in;
    public OutputStream out = System.out;

    public ASCIIComputer(String program) {
        super(program);
    }

    @Override
    protected BigInteger get() {
        try {
            int codePoint = in.read();
            if (codePoint == -1) {
                System.exit(0);
            }
            return BigInteger.valueOf(codePoint);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    protected void put(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0 || value.compareTo(BigInteger.valueOf(0xFF)) > 0) {
            super.put(value);
        } else {
            try {
                out.write(value.intValue());
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void write(int codePoint) {
        BigInteger value = BigInteger.valueOf(codePoint & 0xFF);
        acceptInput(value);
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
            if (codePoint < 0 || codePoint > 0xFF) {
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
