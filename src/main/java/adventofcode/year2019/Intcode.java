package adventofcode.year2019;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Intcode {
    public enum State { READY, AWAITING_INPUT, HALTED };

    private final BigInteger[] initialMem;
    private BigInteger[] mem;
    private int pc;
    private State state;
    public Supplier<BigInteger> in;
    public Consumer<BigInteger> out;

    public Intcode(String input) {
        this(parse(input));
    }

    public Intcode(BigInteger[] initialMem) {
        this.initialMem = initialMem;
        reset();
    }

    public static BigInteger[] parse(String input) {
        return Stream.of(input.split(","))
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
    }

    public BigInteger get(int addr) {
        return mem[addr];
    }

    public void set(int addr, BigInteger value) {
        mem[addr] = value;
    }

    public boolean ready() {
        return state == State.READY;
    }

    public boolean awaitingInput() {
        return state == State.AWAITING_INPUT;
    }

    public boolean halted() {
        return state == State.HALTED;
    }

    public void reset() {
        mem = initialMem.clone();
        pc = 0;
        state = State.READY;
    }

    public BigInteger resume(BigInteger input) {
        if (state != State.AWAITING_INPUT) {
            throw new IllegalStateException();
        }
        mem[mem[pc++].intValue()] = input;
        state = State.READY;
        return resume();
    }

    public BigInteger resume() {
        if (state != State.READY) {
            throw new IllegalStateException();
        }
        while (true) {
            int opcode = mem[pc++].intValue();
            switch (opcode) {
            case 1: // add
                BigInteger a = mem[mem[pc++].intValue()];
                BigInteger b = mem[mem[pc++].intValue()];
                mem[mem[pc++].intValue()] = a.add(b);
                break;
            case 2: // mul
                a = mem[mem[pc++].intValue()];
                b = mem[mem[pc++].intValue()];
                mem[mem[pc++].intValue()] = a.add(b);
                break;
            case 3: // input
                BigInteger input = null;
                if (in != null) {
                    input = in.get();
                }
                if (input == null) {
                    state = State.AWAITING_INPUT;
                    return null;
                } else {
                    mem[mem[pc++].intValue()] = input;
                }
                break;
            case 4: // output
                BigInteger output = mem[mem[pc++].intValue()];
                if (out == null) {
                    return output;
                } else {
                    out.accept(output);
                }
            case 99: // halt
                state = State.HALTED;
                return null;
            }
        }
    }
}
