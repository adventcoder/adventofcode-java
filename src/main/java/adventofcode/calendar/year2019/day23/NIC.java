package adventofcode.calendar.year2019.day23;

import adventofcode.calendar.year2019.common.IntComputer;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NIC extends IntComputer {
    private final Network network;
    private final int addr;
    private Queue<BigInteger> inputs = new ArrayDeque<>();
    private List<BigInteger> outputs = new ArrayList<>();
    private int idleCount;

    public NIC(String program, Network network, int addr) {
        super(program);
        this.network = network;
        this.addr = addr;
        acceptInput(BigInteger.valueOf(addr));
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
    protected BigInteger get() {
        if (inputs.isEmpty()) {
            idleCount++;
            return BigInteger.valueOf(-1);
        } else {
            return inputs.poll();
        }
    }

    @Override
    protected void put(BigInteger output) {
        outputs.add(output);
        if (outputs.size() == 3) {
            network.send(addr, outputs.get(0).intValue(), outputs.get(1), outputs.get(2));
            outputs.clear();
        }
    }
}
