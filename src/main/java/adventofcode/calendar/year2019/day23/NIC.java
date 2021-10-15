package adventofcode.calendar.year2019.day23;

import adventofcode.calendar.year2019.Intcode;

import java.math.BigInteger;
import java.util.*;

public class NIC extends Intcode {
    private final Queue<Packet> bus;
    private final int addr;
    private final Queue<BigInteger> inputs = new ArrayDeque<>();
    private final List<BigInteger> outputs = new ArrayList<>();
    private int idleCount;

    public NIC(String program, Queue<Packet> bus, int addr) {
        super(program);
        this.bus = bus;
        this.addr = addr;
        inputs.add(BigInteger.valueOf(addr));
    }

    public boolean isIdle() {
        return idleCount > 1;
    }

    public void receive(BigInteger x, BigInteger y) {
        inputs.add(x);
        inputs.add(y);
        idleCount = 0;
    }

    @Override
    protected BigInteger read() {
        if (inputs.isEmpty()) {
            idleCount++;
            return BigInteger.valueOf(-1);
        } else {
            return inputs.poll();
        }
    }

    @Override
    protected void write(BigInteger output) {
        outputs.add(output);
        if (outputs.size() == 3) {
            bus.add(new Packet(addr, outputs.get(0).intValue(), outputs.get(1), outputs.get(2)));
            outputs.clear();
        }
    }
}
