package adventofcode.calendar.year2019;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class BufferedIntcode extends Intcode implements Consumer<BigInteger>, IntConsumer, Iterator<BigInteger> {
    private final Deque<BigInteger> in = new ArrayDeque<>();
    private final Deque<BigInteger> out = new ArrayDeque<>();

    public BufferedIntcode(String program) {
        super(program);
    }

    @Override
    protected BigInteger read() {
        return in.remove();
    }

    @Override
    protected void write(BigInteger value) {
        out.add(value);
    }

    public BigInteger peek() {
        while (out.isEmpty() && !halting()) {
            step();
        }
        return out.peek();
    }

    @Override
    public boolean hasNext() {
        return peek() == null;
    }

    @Override
    public BigInteger next() {
        while (out.isEmpty()) {
            step();
        }
        return out.remove();
    }

    @Override
    public void accept(BigInteger value) {
        in.add(value);
    }

    @Override
    public void accept(int value) {
        in.add(BigInteger.valueOf(value));
    }

    public boolean hasNextCodePoint() {
        BigInteger next = peek();
        return next != null && next.signum() >= 0 && next.bitLength() <= 7;
    }

    public int readCodePoint() {
        if (!hasNextCodePoint()) return -1;
        return next().intValue();
    }

    public String readLine() {
        int code = readCodePoint();
        if (code == -1) return null;
        StringBuilder line = new StringBuilder();
        while (code != '\n') {
            line.appendCodePoint(code);
            code = readCodePoint();
            if (code == -1) break;
        }
        return line.toString();
    }

    public String[] readLines() {
        String line = readLine();
        if (line == null) return null;
        List<String> lines = new ArrayList<>();
        while (!line.isEmpty()) {
            lines.add(line);
            line = readLine();
            if (line == null) break;
        }
        return lines.toArray(new String[0]);
    }

    public void writeCodePoint(int code) {
        if (code < 0 || code > 127) {
            throw new IllegalArgumentException("code: " + code);
        }
        accept(BigInteger.valueOf(code));
    }

    public void write(CharSequence seq) {
        int i = 0;
        while (i < seq.length()) {
            int code = Character.codePointAt(seq, i);
            writeCodePoint(code);
            i += Character.charCount(code);
        }
    }

    public void writeLine(CharSequence seq) {
        write(seq);
        writeCodePoint('\n');
    }
}
