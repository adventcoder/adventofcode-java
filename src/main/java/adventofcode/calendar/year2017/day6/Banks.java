package adventofcode.calendar.year2017.day6;

import adventofcode.utils.IntArray;

import java.util.Arrays;

public class Banks {
    private final int[] banks;

    public Banks(String input) {
        banks = IntArray.parse(input, "\\s+");
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(banks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Banks && Arrays.equals(banks, ((Banks) obj).banks);
    }

    public void redistribute() {
        int j = 0;
        for (int i = 1; i < banks.length; i++) {
            if (banks[i] > banks[j]) j = i;
        }
        int q = banks[j] / banks.length;
        int r = banks[j] % banks.length;
        banks[j] = 0;
        for (int i = 0; i < banks.length; i++) {
            banks[i] += q;
        }
        while (r > 0) {
            j = (j + 1) % banks.length;
            banks[j]++;
            r--;
        }
    }
}
