package adventofcode.calendar.year2019.day23;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;
import java.util.*;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Queue<Packet> bus = new ArrayDeque<>();
        NIC[] nics = new NIC[50];
        for (int i = 0; i < nics.length; i++) {
            nics[i] = new NIC(input, bus, i);
        }
        NAT nat = new NAT(bus, nics);
        Set<BigInteger> seen = new HashSet<>();
        while (true) {
            for (NIC nic : nics) {
                nic.step();
            }
            nat.step();
            while (!bus.isEmpty()) {
                Packet packet = bus.remove();
                if (packet.from == 255 && packet.to == 0 && !seen.add(packet.y)) {
                    return packet.y;
                }
                if (packet.to == 255) {
                    nat.receive(packet.x, packet.y);
                } else {
                    nics[packet.to].receive(packet.x, packet.y);
                }
            }
        }
    }
}
