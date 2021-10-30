package adventofcode.calendar.year2018.day19;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<Integer> {
    @Override
    public Integer solve(String input) {
        // The actual program to compute the divisor sum starts at instruction 1.
        // Run up to that point to set up values in the register.
        Process process = new Process(input);
        process.reg[0] = 1;
        while (process.ip != 1) {
            process.step();
        }
        return divisorSum(process.reg[3]);
    }

    public int divisorSum(int n) {
        int sum = 1;
        if ((n & 1) == 0) {
            int evenPower = n & (-n);
            sum *= (evenPower << 1) - 1;
            n /= evenPower;
        }
        int p = 3;
        while (p * p <= n) {
            if (n % p == 0) {
                int primePower = 1;
                do {
                    primePower *= p;
                    n /= p;
                } while (n % p == 0);
                sum *= (primePower * p - 1) / (p - 1);
            }
            p += 2;
        }
        if (n > 1) {
            sum *= n + 1;
        }
        return sum;
    }
}
