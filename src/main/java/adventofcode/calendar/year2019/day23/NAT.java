package adventofcode.calendar.year2019.day23;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Queue;

import static adventofcode.utils.Iterables.all;

public class NAT {
    private final Queue<Packet> bus;
    private final NIC[] nics;
    private BigInteger x;
    private BigInteger y;

    public NAT(Queue<Packet> bus, NIC[] nics) {
        this.nics = nics;
        this.bus = bus;
    }

    public void receive(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public void step() {
        if (isIdle()) {
            bus.add(new Packet(255, 0, x, y));
            x = null;
            y = null;
        }
    }

    private boolean isIdle() {
        return all(NIC::isIdle, Arrays.asList(nics));
    }
}
