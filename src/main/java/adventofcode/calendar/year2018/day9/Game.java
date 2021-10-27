package adventofcode.calendar.year2018.day9;

import adventofcode.utils.IntArray;

public class Game {
    public int players;
    public int last;

    public Game(String input) {
        String[] words = input.split("\\s+");
        players = Integer.parseInt(words[0]);
        last = Integer.parseInt(words[words.length - 2]);
    }

    public Long highScore() {
        long[] scores = new long[players];
        int[] left = new int[last + 1];
        int[] right = new int[last + 1];
        left[0] = right[0] = 0;
        int curr = 0;
        for (int n = 1; n <= last; n++) {
            if (n % 23 == 0) {
                int i = (n - 1) % scores.length;
                scores[i] += n;
                curr = left[left[left[left[left[left[curr]]]]]];
                scores[i] += left[curr];
                left[curr] = left[left[curr]];
                right[left[curr]] = curr;
            } else {
                int before = right[curr];
                int after = right[before];
                left[n] = before;
                right[n] = after;
                right[before] = n;
                left[after] = n;
                curr = n;
            }
        }
        return IntArray.reduce(Math::max, scores);
    }
}
