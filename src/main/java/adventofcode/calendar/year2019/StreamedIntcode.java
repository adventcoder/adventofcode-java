package adventofcode.calendar.year2019;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.NoSuchElementException;

public class StreamedIntcode extends Intcode {
    public static void main(String[] args) {
        run(String.join(" ", args), System.in, System.out);
    }

    public static void run(String program, InputStream in, OutputStream out) {
        new StreamedIntcode(program, in, out).run();
    }

    private final InputStream in;
    private final OutputStream out;

    public StreamedIntcode(String program, InputStream in, OutputStream out) {
        super(program);
        this.in = in;
        this.out = out;
    }

    @Override
    protected BigInteger read() {
        try {
            return BigInteger.valueOf(in.read());
        } catch (IOException ioe) {
            throw new NoSuchElementException(ioe.getMessage());
        }
    }

    @Override
    protected void write(BigInteger value) {
        try {
            out.write(value.byteValue());
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
