package adventofcode.calendar.year2019;

import java.math.BigInteger;
import java.util.Arrays;

public class IntComputer {
    private final BigInteger[] originalMem;
    private BigInteger[] mem = new BigInteger[0];
    private int ip = -1;
    private boolean yielded = false;

    public IntComputer(String program) {
        originalMem = parse(program);
        reset();
    }

    private static BigInteger[] parse(String program) {
        String[] tokens = program.split(",");
        BigInteger[] mem = new BigInteger[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            mem[i] = new BigInteger(tokens[i]);
        }
        return mem;
    }

    public BigInteger get(int i) {
        if (i > mem.length) return BigInteger.ZERO;
        return mem[i];
    }

    public void set(int i, BigInteger value) {
        if (i >= mem.length) {
            int newLength = mem.length;
            do {
                newLength *= 2;
            } while (i < newLength);
            BigInteger[] newMem = Arrays.copyOf(mem, newLength);
            Arrays.fill(newMem, mem.length, newMem.length, BigInteger.ZERO);
            mem = newMem;
        }
        mem[i] = value;
    }

    public void reset() {
        mem = originalMem.clone();
        ip = 0;
    }

    public boolean halted() {
        return ip < 0;
    }

    public boolean waitingForInput() {
        return yielded && getOp() == 3;
    }

    public boolean waitingForOutput() {
        return yielded && getOp() == 4;
    }

    public BigInteger resume() {
        if (!waitingForOutput()) throw new IllegalStateException();
        BigInteger output = getParameter(1);
        ip += 2;
        yielded = false;
        return output;
    }

    public void resume(BigInteger input) {
        if (!waitingForInput()) throw new IllegalStateException();
        set(getParameterAddress(1), input);
        ip += 2;
        yielded = false;
    }

    public void step() {
        if (yielded) {
            throw new IllegalStateException("yielded");
        }
        switch (getOp()) {
        case 1: // add
            set(getParameterAddress(3), getParameter(1).add(getParameter(2)));
            ip += 4;
            break;
        case 2: // multiply
            set(getParameterAddress(3), getParameter(1).multiply(getParameter(2)));
            ip += 4;
            break;
        case 3: // input
            yielded = true;
            break;
        case 4: // output
            yielded = true;
            break;
        case 5: // jump-if-true
            if (!getParameter(1).equals(BigInteger.ZERO)) {
                ip = getParameter(2).intValue();
            } else {
                ip += 3;
            }
            break;
        case 6: // jump-if-false
            if (getParameter(1).equals(BigInteger.ZERO)) {
                ip = getParameter(2).intValue();
            } else {
                ip += 3;
            }
            break;
        case 7: // less than
            set(getParameterAddress(3), getParameter(1).compareTo(getParameter(2)) < 0 ? BigInteger.ONE : BigInteger.ZERO);
            ip += 4;
            break;
        case 8: // equals
            set(getParameterAddress(3), getParameter(1).equals(getParameter(2)) ? BigInteger.ONE : BigInteger.ZERO);
            ip += 4;
            break;
        case 99: // halt
            ip = -1;
            break;
        default:
            throw new RuntimeException();
        }
    }

    private int getOp() {
        return get(ip).intValue() % 100;
    }

    private BigInteger getParameter(int i) {
        return get(getParameterAddress(i));
    }

    private int getParameterAddress(int i) {
        switch (getParameterMode(i)) {
        case 0: // position mode
            return get(ip + i).intValue();
        case 1: // immediate mode
            return ip + i;
        default:
            throw new RuntimeException();
        }
    }

    public int getParameterMode(int i) {
        int value = get(ip).intValue() / 100;
        while (i > 1) {
            value /= 10;
            i--;
        }
        return value % 10;
    }
}
