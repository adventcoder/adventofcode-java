package adventofcode.calendar.year2019;

import adventofcode.utils.IntMath;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Intcode implements Runnable {
    public static BigInteger[] parse(String program) {
        return Stream.of(program.split(",")).map(BigInteger::new).toArray(BigInteger[]::new);
    }

    public static Consumer<BigInteger> inputConsumer(String program, Consumer<BigInteger> out) {
        Queue<BigInteger> inputs = new ArrayDeque<>();
        Intcode code = new Intcode(program) {
            @Override
            protected BigInteger read() {
                return inputs.remove();
            }

            @Override
            protected void write(BigInteger value) {
                out.accept(value);
            }
        };
        return (input) -> {
            if (input == null) {
                code.run();
            } else {
                inputs.add(input);
                while (!inputs.isEmpty()) {
                    if (code.halting()) throw new IllegalStateException();
                    code.step();
                }
            }
        };
    }

    public static Iterator<BigInteger> outputIterator(String program, Iterator<BigInteger> in) {
        Queue<BigInteger> out = new ArrayDeque<>(1);
        Intcode code = new Intcode(program) {
            @Override
            protected BigInteger read() {
                return in.next();
            }

            @Override
            protected void write(BigInteger value) {
                out.add(value);
            }
        };
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                awaitOutput();
                return !out.isEmpty();
            }

            @Override
            public BigInteger next() {
                awaitOutput();
                return out.remove();
            }

            private void awaitOutput() {
                while (!code.halting() && out.isEmpty()) {
                    code.step();
                }
            }
        };
    }

    public static Iterable<BigInteger> outputs(String program, Iterable<BigInteger> inputs) {
        return () -> outputIterator(program, inputs.iterator());
    }

    public static Iterable<BigInteger> outputs(String program, int... inputs) {
        return () -> outputIterator(program, new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < inputs.length;
            }

            @Override
            public BigInteger next() {
                return BigInteger.valueOf(inputs[i++]);
            }
        });
    }

    private BigInteger[] memory;
    private int pc = 0;
    private int rb = 0;

    public Intcode(BigInteger[] code) {
        this.memory = code.clone();
    }

    public Intcode(String program) {
        this.memory = parse(program);
    }

    public boolean halting() {
        return getOpCode() == 99;
    }

    @Override
    public void run() {
        while (!halting()) {
            step();
        }
    }

    public void step() {
        int opCode = getOpCode();
        switch (opCode) {
        case 1: // add
            setParam(3, getParam(1).add(getParam(2)));
            pc += 4;
            break;
        case 2: // mul
            setParam(3, getParam(1).multiply(getParam(2)));
            pc += 4;
            break;
        case 3: // input
            setParam(1, read());
            pc += 2;
            break;
        case 4: // output
            write(getParam(1));
            pc += 2;
            break;
        case 5: // jump-if-true
            if (!getParam(1).equals(BigInteger.ZERO)) {
                pc = getParam(2).intValue();
            } else {
                pc += 3;
            }
            break;
        case 6: // jump-if-false
            if (getParam(1).equals(BigInteger.ZERO)) {
                pc = getParam(2).intValue();
            } else {
                pc += 3;
            }
            break;
        case 7: // less than
            setParam(3, getParam(1).compareTo(getParam(2)) < 0 ? BigInteger.ONE : BigInteger.ZERO);
            pc += 4;
            break;
        case 8: // equals
            setParam(3, getParam(1).equals(getParam(2)) ? BigInteger.ONE : BigInteger.ZERO);
            pc += 4;
            break;
        case 9: // adjust relative base
            rb += getParam(1).intValue();
            pc += 2;
            break;
        case 99:
            throw new NoSuchElementException();
        default:
            throw new IllegalArgumentException("opCode: " + opCode);
        }
    }

    protected BigInteger read() {
        throw new NoSuchElementException();
    }

    protected void write(BigInteger value) {
        throw new IllegalStateException();
    }

    public BigInteger get(int i) {
        return i < memory.length ? memory[i] : BigInteger.ZERO;
    }

    public int getAsInt(int i) {
        return get(i).intValueExact();
    }

    public void set(int i, BigInteger value) {
        if (i >= memory.length) {
            if (i == Integer.MAX_VALUE) throw new IndexOutOfBoundsException(i);
            BigInteger[] newMemory = Arrays.copyOf(memory, IntMath.ceilPowerOfTwoMinusOne(i + 1));
            Arrays.fill(newMemory, memory.length, newMemory.length, BigInteger.ZERO);
            memory = newMemory;
        }
        memory[i] = value;
    }

    public void set(int i, int value) {
        set(i, BigInteger.valueOf(value));
    }

    private int getOpCode() {
        return getAsInt(pc) % 100;
    }

    private int getParamMode(int n) {
        return getAsInt(pc) / (100 * IntMath.pow10(n - 1)) % 10;
    }

    private BigInteger getParam(int n) {
        return get(getParamAddress(n));
    }

    private void setParam(int n, BigInteger value) {
        set(getParamAddress(n), value);
    }

    private int getParamAddress(int n) {
        int mode = getParamMode(n);
        switch (mode) {
        case 0:
            return getAsInt(pc + n);
        case 1:
            return pc + n;
        case 2:
            return getAsInt(pc + n) + rb;
        default:
            throw new IllegalArgumentException("mode: " + mode);
        }
    }
}
