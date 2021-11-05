package adventofcode.calendar.year2017.day9;

import adventofcode.framework.AbstractPart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        input = input.replaceAll("!.", "");
        int length = 0;
        Matcher matcher = Pattern.compile("<([^>]*)>").matcher(input);
        while (matcher.find()) {
            length += matcher.group().length() - 2;
        }
        return length;
    }
}
