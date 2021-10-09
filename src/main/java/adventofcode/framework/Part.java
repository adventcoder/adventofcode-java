package adventofcode.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Part<T> {
    int year();
    int day();
    String getInput(Session session) throws IOException;
    T solve(String input);

    @SuppressWarnings("rawtypes")
    static void main(Class<? extends Part> partClass, String[] args) throws ReflectiveOperationException, IOException {
        printAnswer(newInstance(partClass, args));
    }

    @SuppressWarnings("rawtypes")
    static Part newInstance(Class<? extends Part> partClass, String[] args) throws ReflectiveOperationException {
        try {
            return partClass.getDeclaredConstructor(String[].class).newInstance(new Object[] { args });
        } catch (NoSuchMethodException e) {
            return partClass.getDeclaredConstructor().newInstance();
        }
    }

    static void printAnswer(Part<?> part) throws IOException {
        Session session = Session.getInstance();
        String input = part.getInput(session);
        long startTime = System.nanoTime();
        Object answer = part.solve(input);
        long answerTime = System.nanoTime() - startTime;
        printAnswer("Answer", answer, answerTime);
    }

    private static void printAnswer(String prefix, Object answer, long answerTime) {
        String[] lines = formatAnswer(answer).split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (prefix != null) {
                if (i == 0) {
                    System.out.print(prefix);
                    System.out.print(": ");
                } else {
                    System.out.print(prefix.replaceAll(".", " "));
                    System.out.print("  ");
                }
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

    private static String formatAnswer(Object answer) {
        return answer == null ? "<No answer>" : answer.toString();
    }

    private static String formatTime(long nanoTime) {
        long s = nanoTime / 1000000000;
        double ms = nanoTime % 1000000000 / 1000000.0;
        List<String> units = new ArrayList<>();
        if (s != 0) units.add(s + " s");
        if (ms != 0.0 || units.isEmpty()) units.add(String.format("%.3f ms", ms));
        return String.join(" ", units);
    }
}
