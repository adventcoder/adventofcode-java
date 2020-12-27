package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;

import java.util.function.Consumer;

public class Day1 extends AbstractDay {
    public Day1() {
        super(2019, 1);
    }

    @Override
    public void solve(String input, Consumer<String> answers) {
        int answer1 = 0;
        int answer2 = 0;
        for (String line : input.split("\n")) {
            int mass = Integer.parseInt(line);
            int fuel = mass / 3 - 2;
            answer1 += fuel;
            do {
                answer2 += fuel;
                fuel = fuel / 3 - 2;
            } while (fuel > 0);
        }
        answers.accept(Integer.toString(answer1));
        answers.accept(Integer.toString(answer2));
    }
}
