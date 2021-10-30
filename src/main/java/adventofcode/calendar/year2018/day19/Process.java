package adventofcode.calendar.year2018.day19;

import adventofcode.calendar.year2018.day16.Operator;

import java.util.*;

public class Process {
    private static final List<String> opNames = Arrays.asList("addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtir", "gtri", "gtrr", "eqir", "eqri", "eqrr");

    public List<int[]> instructions = new ArrayList<>();
    public int ipReg = -1;
    public int[] reg = new int[6];
    public int ip = 0;

    public Process(String input) {
        for (String line : input.split("\n")) {
            String[] tokens = line.split("\\s+");
            if (tokens[0].equals("#ip")) {
                ipReg = Integer.parseInt(tokens[1]);
            } else {
                int code = opNames.indexOf(tokens[0]);
                int a = Integer.parseInt(tokens[1]);
                int b = Integer.parseInt(tokens[2]);
                int c = Integer.parseInt(tokens[3]);
                instructions.add(new int[] { code, a, b, c });
            }
        }
    }

    public boolean running() {
        return ip >= 0 && ip < instructions.size();
    }

    public void step() {
        if (ipReg >= 0) {
            reg[ipReg] = ip;
        }
        int[] instr = instructions.get(ip);
        Operator.values.get(instr[0]).apply(instr, reg);
        if (ipReg >= 0) {
            ip = reg[ipReg];
        }
        ip++;
    }
}
