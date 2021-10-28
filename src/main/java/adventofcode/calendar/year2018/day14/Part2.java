package adventofcode.calendar.year2018.day14;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        // This is slow...
        StringBuilder scores = new StringBuilder("37");
        int i = 0;
        int j = 1;
        while (true) {
            int a = scores.charAt(i) - '0';
            int b = scores.charAt(j) - '0';
            int c = a + b;
            if (c >= 10) {
                scores.append(c / 10);
                if (endsWith(scores, input)) {
                    return scores.length() - input.length();
                }
                c %= 10;
            }
            scores.append(c);
            if (endsWith(scores, input)) {
                return scores.length() - input.length();
            }
            i = (i + a + 1) % scores.length();
            j = (j + b + 1) % scores.length();
        }
    }

    private boolean endsWith(StringBuilder scores, String sequence) {
        if (scores.length() < sequence.length()) {
            return false;
        }
        for (int i = 0; i < sequence.length(); i++) {
            if (scores.charAt(scores.length() - 1 - i) != sequence.charAt(sequence.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
