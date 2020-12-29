package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;

import java.util.function.Consumer;

public class Day5 extends AbstractDay {
    public Day5() {
        super(2019, 5);
    }

    @Override
    public void solve(String input, Consumer<Object> answers) {
        IntcodeVM vm = new IntcodeVM(input);
        vm.join();
    }
}
