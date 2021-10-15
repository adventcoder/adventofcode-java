package adventofcode.calendar.year2019.day23;

import java.math.BigInteger;

public class Packet {
    public final int from;
    public final int to;
    public final BigInteger x;
    public final BigInteger y;

    public Packet(int from, int to, BigInteger x, BigInteger y) {
        this.from = from;
        this.to = to;
        this.x = x;
        this.y = y;
    }
}
