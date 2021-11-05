package adventofcode.calendar.year2017.day9;

import adventofcode.framework.AbstractPart;

public class Part1 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        input = input.replaceAll("!.", "");
        input = input.replaceAll("<([^>]*)>", "");
        System.out.println(input);
        int score = 0;
        int depth = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') {
                depth++;
            } else if (input.charAt(i) == '}') {
                score += depth;
                depth--;
            }
        }
        return score;
    }
}
