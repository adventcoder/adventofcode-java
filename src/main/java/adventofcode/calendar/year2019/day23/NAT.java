package adventofcode.calendar.year2019.day23;

import java.math.BigInteger;

public class NAT {
    private final Network network;
    private BigInteger x;
    private BigInteger y;

    public NAT(Network network) {
        this.network = network;
    }

    public void receive(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public void step() {
        if (network.isIdle()) {
            network.send(255, 0, x, y);
            x = null;
            y = null;
        }
    }
}
