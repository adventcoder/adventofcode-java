package adventofcode.calendar.year2018.day16;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Operator {
    List<Operator> values = Arrays.asList(Operator::addr, Operator::addi, Operator::mulr, Operator::muli, Operator::banr, Operator::bani, Operator::borr, Operator::bori, Operator::setr, Operator::seti, Operator::gtir, Operator::gtri, Operator::gtrr, Operator::eqri, Operator::eqir, Operator::eqrr);

    void apply(int[] instr, int[] reg);

    static void addr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] + reg[instr[2]];
    }

    static void addi(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] + instr[2];
    }

    static void mulr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] * reg[instr[2]];
    }

    static void muli(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] * instr[2];
    }

    static void banr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] & reg[instr[2]];
    }

    static void bani(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] & instr[2];
    }

    static void borr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] | reg[instr[2]];
    }

    static void bori(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] | instr[2];
    }

    static void setr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]];
    }

    static void seti(int[] instr, int[] reg) {
        reg[instr[3]] = instr[1];
    }

    static void gtir(int[] instr, int[] reg) {
        reg[instr[3]] = instr[1] > reg[instr[2]] ? 1 : 0;
    }

    static void gtri(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] > instr[2] ? 1 : 0;
    }

    static void gtrr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] > reg[instr[2]] ? 1 : 0;
    }

    static void eqir(int[] instr, int[] reg) {
        reg[instr[3]] = instr[1] == reg[instr[2]] ? 1 : 0;
    }

    static void eqri(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] == instr[2] ? 1 : 0;
    }

    static void eqrr(int[] instr, int[] reg) {
        reg[instr[3]] = reg[instr[1]] == reg[instr[2]] ? 1 : 0;
    }
}
