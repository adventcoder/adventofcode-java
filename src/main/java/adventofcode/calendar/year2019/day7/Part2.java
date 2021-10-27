package adventofcode.calendar.year2019.day7;

import adventofcode.framework.AbstractPart;
import adventofcode.utils.IntArray;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Part2 extends AbstractPart<BigInteger> {
    @Override
    public BigInteger solve(String input) {
        BigInteger maxSignal = BigInteger.ZERO;
        int[] settings = IntStream.range(5, 10).toArray();
        ExecutorService executorService = Executors.newFixedThreadPool(settings.length);
        try {
            while (IntArray.nextPermutation(settings)) {
                BigInteger signal = run(executorService, input, settings);
                if (signal.compareTo(maxSignal) > 0) {
                    maxSignal = signal;
                }
            }
        } finally {
            executorService.shutdown();
        }
        return maxSignal;
    }

    private BigInteger run(ExecutorService executorService, String program, int[] settings) {
        List<BlockingQueue<BigInteger>> queues = new ArrayList<>();
        for (int setting : settings) {
            BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>();
            queue.add(BigInteger.valueOf(setting));
            queues.add(queue);
        }
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < queues.size(); i++) {
            BlockingQueue<BigInteger> in = queues.get(i);
            BlockingQueue<BigInteger> out = queues.get((i + 1) % queues.size());
            futures.add(executorService.submit(new Amplifier(program, i, in, out)));
        }
        queues.get(0).add(BigInteger.ZERO);
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new AssertionError(e);
            }
        }
        return queues.get(0).remove();
    }
}
