package adventofcode.calendar.year2019.day23;

import java.math.BigInteger;

public class Answer extends RuntimeException {
    public final BigInteger value;

    public Answer(BigInteger value) {
        this.value = value;
    }
}
