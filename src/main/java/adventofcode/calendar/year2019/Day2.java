package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;

import java.math.BigInteger;
import java.util.function.Consumer;

public class Day2 extends AbstractDay {
    public Day2() {
        super(2019, 2);
    }

    @Override
    public void solve(String input, Consumer<Object> answers) {
        IntcodeVM vm = new IntcodeVM(input);
        answers.accept(output(vm, 1202));
        answers.accept(findInput(vm, 19690720));
    }
    
    private int output(IntcodeVM vm, int input) {
        vm.reset();
        vm.set(1, BigInteger.valueOf(input / 100));
        vm.set(2, BigInteger.valueOf(input % 100));
        vm.join();
        return vm.get(0).intValue();
    }

    private Integer findInput(IntcodeVM vm, int output) {
        for (int input = 0; input < 10000; input++) {
            if (output(vm, input) == output) {
                return input;
            }
        }
        return null;
    }
}
