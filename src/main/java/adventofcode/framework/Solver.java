package adventofcode.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver<T> {
    public abstract T solve(String input);

    public void printAnswer(String input) {
        long startTime = System.nanoTime();
        T answer = solve(input);
        long answerTime = System.nanoTime() - startTime;
        printAnswer("Answer:", answer, answerTime);
    }

    public void printAnswer(String prefix, T answer, long answerTime) {
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
                System.out.print(formatTime(answerTime));
                System.out.print("]");
            }
            System.out.println();
        }
    }

    private String formatAnswer(T answer) {
        return answer == null ? "<No answer>" : answer.toString();
    }

    private String formatTime(long nanoTime) {
        long s = nanoTime / 1000000000;
        double ms = nanoTime % 1000000000 / 1000000.0;
        List<String> units = new ArrayList<>();
        if (s != 0) units.add(s + " s");
        if (ms != 0.0 || units.isEmpty()) units.add(String.format("%.3f ms", ms));
        return String.join(" ", units);
    }
}
