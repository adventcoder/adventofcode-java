package adventofcode.calendar.year2019.common;

import adventofcode.utils.IntMath;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntComputer {
    private BigInteger[] mem;
    private int pc;
    private int rb;

    public IntComputer(String program) {
        this.mem = parseMem(program);
        this.pc = 0;
        this.rb = 0;
    }

    private static BigInteger[] parseMem(String input) {
        String[] tokens = input.split(",");
        BigInteger[] mem = new BigInteger[tokens.length];
        for (int i = 0; i < mem.length; i++) { 
            mem[i] = new BigInteger(tokens[i].trim());
        }
        return mem;
    }

    public BigInteger get(int address) {
        if (address >= mem.length) {
            return BigInteger.ZERO;
        }
        return mem[address];
    }

    public void set(int address, BigInteger value) {
        if (address >= mem.length) {
            int maxAddress = IntMath.ceilPowerOfTwoMinusOne(address);
            BigInteger[] newMem = Arrays.copyOf(mem, maxAddress + 1);
            Arrays.fill(newMem, mem.length, newMem.length, BigInteger.ZERO);
            mem = newMem;
        }
        mem[address] = value;
    }

    private int getOp() {
        return mem[pc].intValue() % 100;
    }

    private BigInteger getArg(int i) {
        return get(getArgAddress(i));
    }

    private void setArg(int i, BigInteger value) {
        set(getArgAddress(i), value);
    }

    private int getArgAddress(int i) {
        int mode = getArgMode(i);
        if (mode == 0) {
            return mem[pc + i].intValue();
        } else if (mode == 1) {
            return pc + i;
        } else if (mode == 2) {
            return mem[pc + i].intValue() + rb;
        } else {
            throw new RuntimeException();
        }
    }

    private int getArgMode(int i) {
        return mem[pc].intValue() / (100 * IntMath.pow10(i - 1)) % 10;
    }

    public void step() {
        switch (getOp()) {
        case 1: // add
            set(getArgAddress(3), getArg(1).add(getArg(2)));
            pc += 4;
            break;
        case 2: // mul
            set(getArgAddress(3), getArg(1).multiply(getArg(2)));
            pc += 4;
            break;
        case 3: // input
            setArg(1, get());
            pc += 2;
            break;
        case 4: // output
            put(getArg(1));
            pc += 2;
            break;
        case 5: // jump-if-true
            if (!getArg(1).equals(BigInteger.ZERO)) {
                pc = getArg(2).intValue();
            } else {
                pc += 3;
            }
            break;
        case 6: // jump-if-false
            if (getArg(1).equals(BigInteger.ZERO)) {
                pc = getArg(2).intValue();
            } else {
                pc += 3;
            }
            break;
        case 7: // less than
            setArg(3, getArg(1).compareTo(getArg(2)) < 0 ? BigInteger.ONE : BigInteger.ZERO);
            pc += 4;
            break;
        case 8: // equals
            setArg(3, getArg(1).equals(getArg(2)) ? BigInteger.ONE : BigInteger.ZERO);
            pc += 4;
            break;
        case 9: // adjust relative base
            rb += getArg(1).intValue();
            pc += 2;
            break;
        case 99:
            halt();
            pc += 1;
            break;
        default:
            throw new UnhandledOperationException();
        }
    }

    protected BigInteger get() {
        throw new UnhandledOperationException();
    }

    protected void put(BigInteger value) {
        throw new UnhandledOperationException();
    }

    protected void halt() {
        throw new UnhandledOperationException();
    }

    public String state() {
        if (inputting()) return "inputting";
        if (outputting()) return "outputting";
        if (halting()) return "halting";
        return "running";
    }

    public boolean halting() {
        return getOp() == 99;
    }

    public boolean inputting() {
        return getOp() == 3;
    }

    public boolean outputting() {
        return getOp() == 4;
    }

    public void run() {
        while (!halting()) {
            step();
        }
    }

    public void acceptInput(BigInteger input) {
        while (!inputting()) {
            step();
        }
        setArg(1, input);
        pc += 2;
    }

    public BigInteger peekOutput() {
        while (!outputting() && !halting()) {
            step();
        }
        if (halting()) return null;
        return getArg(1);
    }

    public boolean hasNextOutput() {
        return peekOutput() != null;
    }

    public BigInteger nextOutput() {
        BigInteger output = peekOutput();
        if (output == null) {
            throw new NoSuchElementException();
        }
        pc += 2;
        return output;
    }

    public BigInteger peekUnhandledOutput() {
        try {
            run();
            return null;
        } catch (UnhandledOperationException e) {
            if (!outputting()) throw e;
            return getArg(1);
        }
    }

    public boolean hasNextUnhandledOutput() {
        return peekUnhandledOutput() != null;
    }

    public BigInteger nextUnhandledOutput() {
        BigInteger output = peekUnhandledOutput();
        if (output == null) {
            throw new NoSuchElementException();
        }
        pc += 2;
        return output;
    }
}
