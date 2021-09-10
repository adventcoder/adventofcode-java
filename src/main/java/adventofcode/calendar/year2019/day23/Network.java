package adventofcode.calendar.year2019.day23;

import java.math.BigInteger;

public class Network {
    private NIC[] nics;
    private NAT nat;

    public Network(String program) {
        this.nics = new NIC[50];
        for (int addr = 0; addr < nics.length; addr++) {
            nics[addr] = new NIC(program, this, addr);
        }
        this.nat = new NAT(this);
    }

    public boolean isIdle() {
        for (NIC nic : nics) {
            if (!nic.isIdle()) {
                return false;
            }
        }
        return true;
    }

    public void step() {
        for (NIC nic : nics) {
            nic.step();
        }
        nat.step();
    }

    public void send(int from, int to, BigInteger x, BigInteger y) {
        if (to == 255) {
            nat.receive(x, y);
        } else {
            nics[to].receive(x, y);
        }
    }

    public BigInteger answer() {
        while (true) {
            try {
                step();
            } catch (Answer answer) {
                return answer.value;
            }
        }
    }
}
