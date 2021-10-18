package adventofcode.calendar.year2019.day22;

import java.math.BigInteger;
import java.util.InputMismatchException;

/**
 * Represents a shuffle as a linear transform of an index to a new index: s(i) = a*i + b. 
 */
public class Shuffle {
    public final BigInteger size;
    private BigInteger a = BigInteger.ONE;
    private BigInteger b = BigInteger.ZERO;

    public Shuffle(BigInteger size) {
        this.size = size;
    }

    /**
     * Given an index into a deck, returns the new index after applying the shuffle.
     */
    public BigInteger apply(BigInteger index) {
        return a.multiply(index).add(b).mod(size);
    }

    /**
     * Given an index into a shuffled deck returns what the index would have been in the original deck.
     */
    public BigInteger applyInverse(BigInteger index) {
        return index.subtract(b).multiply(a.modInverse(size)).mod(size);
    }

    public void dealIntoNewStack() {
        // s'(i) = -s(i) - 1 = (-a)*i + (-b-1)
        a = a.negate().mod(size);
        b = b.add(BigInteger.ONE).negate().mod(size);
    }

    public void cut(BigInteger n) {
        // s'(i) = s(i) - n = a*i + (b-n)
        b = b.subtract(n).mod(size);
    }

    public void dealWithIncrement(BigInteger n) {
        // s'(i) = s(i)*n = (a*n)*i + (b*n)
        a = a.multiply(n).mod(size);
        b = b.multiply(n).mod(size);
    }

    public void perform(String input) {
        for (String line : input.split("\n")) {
            String[] tokens = line.split("\\s+");
            if (tokens.length == 4 && tokens[0].equals("deal") && tokens[1].equals("with")) {
                dealWithIncrement(new BigInteger(tokens[3]));
            } else if (tokens.length == 4 && tokens[0].equals("deal") && tokens[1].equals("into")) {
                dealIntoNewStack();
            } else if (tokens.length == 2 && tokens[0].equals("cut")) {
                cut(new BigInteger(tokens[1]));
            } else {
                throw new InputMismatchException(line);
            }
        }
    }

    public void repeat(BigInteger n) {
        // s'(i) = s^n(i) = a^n*i + (a^(n-1)+...+1)*b
        BigInteger aPower = BigInteger.ONE;
        BigInteger aPowerSum = BigInteger.ZERO;
        for (int i = n.bitLength() - 1; i >= 0; i--) {
            aPowerSum = aPowerSum.multiply(aPower).add(aPowerSum).mod(size);
            aPower = aPower.multiply(aPower).mod(size);
            if (n.testBit(i)) {
                aPowerSum = aPowerSum.add(aPower).mod(size);
                aPower = aPower.multiply(a).mod(size);
            }
        }
        this.a = aPower;
        this.b = aPowerSum.multiply(b).mod(size);
    }
}
