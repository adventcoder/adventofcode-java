package adventofcode.calendar.year2019.day23;

import adventofcode.framework.AbstractPart;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

public class Part1 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        Queue<Packet> bus = new ArrayDeque<>();
        NIC[] nics = new NIC[50];
        for (int i = 0; i < nics.length; i++) {
            nics[i] = new NIC(input, bus, i);
        }
        while (true) {
            for (NIC nic : nics) {
                nic.step();
            }
            while (!bus.isEmpty()) {
                Packet packet = bus.remove();
                if (packet.to == 255) {
                    return packet.y;
                }
                nics[packet.to].receive(packet.x, packet.y);
            }
        }
    }
}
