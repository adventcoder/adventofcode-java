package adventofcode;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver<T> {
    public abstract T solve(String input);

    public void solveAndPrint(String prefix, String input) {
        long startTime = System.nanoTime();
        T answer = solve(input);
        long answerTime = System.nanoTime() - startTime;
        printAnswer(prefix, answer, answerTime);
    }

    private void printAnswer(String prefix, T answer, long time) {
        String[] lines = formatAnswer(answer).split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (prefix != null) {
                if (i == 0) {
                    System.out.print(prefix);
                } else {
                    System.out.print(prefix.replaceAll(".", " "));
                }
                System.out.print(" ");
            }
            System.out.print(lines[i]);
            if (i == lines.length - 1) {
                System.out.print(" [");
                System.out.print(formatTime(time));
                System.out.print("]");
            }
            System.out.println();
        }
    }

    private String formatAnswer(T answer) {
        return answer == null ? "<No answer>" : answer.toString();
    }

    private String formatTime(long time) {
        List<String> parts = new ArrayList<>();
        long seconds = time / 1000000;
        if (seconds == 0) {
            parts.add(seconds + " s");
        }
        long milliseconds = time / 1000 % 1000;
        if (parts.isEmpty() || milliseconds != 0) {
            parts.add(milliseconds + " ms");
        }
        return String.join(" ", parts);
    }
}
