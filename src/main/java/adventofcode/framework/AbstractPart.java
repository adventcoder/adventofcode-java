package adventofcode.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPart<T> implements Solver<T> {
    public static void main(String[] args) throws Exception {
        Class<?> mainClass = Class.forName(System.getProperty("sun.java.command"));
        if (!AbstractPart.class.isAssignableFrom(mainClass)) {
            throw new IllegalArgumentException();
        }
        AbstractPart<?> solver = (AbstractPart<?>) mainClass.newInstance();
        solver.parseArgs(args);
        solver.printAnswer();
    }

    public void parseArgs(String[] args) {
    }

    public int getYear() {
        String[] parts = getClass().getPackageName().split("\\.");
        if (parts.length < 2 || !parts[parts.length - 2].matches("year\\d+")) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(parts[parts.length - 2].substring(4));
    }

    public int getDay() {
        String[] parts = getClass().getPackageName().split("\\.");
        if (parts.length < 1 || !parts[parts.length - 1].matches("day\\d+")) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(parts[parts.length - 1].substring(3));
    }

    public String getInput(Session session) throws IOException {
        return session.getInput(getYear(), getDay());
    }

    public void printAnswer() throws IOException {
        Session session = Session.getInstance();
        String input = getInput(session);
        long startTime = System.nanoTime();
        T answer = solve(input);
        long answerTime = System.nanoTime() - startTime;
        printAnswer("Answer:", answer, answerTime);
    }

    public abstract T solve(String input);

    private void printAnswer(String prefix, T answer, long answerTime) {
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
