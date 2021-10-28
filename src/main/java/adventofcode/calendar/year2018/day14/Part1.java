package adventofcode.calendar.year2018.day14;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<String> {
    @Override
    public String solve(String input) {
        int n = Integer.parseInt(input);
        StringBuilder scores = new StringBuilder("37");
        int i = 0;
        int j = 1;
        while (scores.length() < n + 10) {
            int a = scores.charAt(i) - '0';
            int b = scores.charAt(j) - '0';
            scores.append(a + b);
            i = (i + a + 1) % scores.length();
            j = (j + b + 1) % scores.length();
        }
        return scores.substring(n, n + 10);
    }
}
