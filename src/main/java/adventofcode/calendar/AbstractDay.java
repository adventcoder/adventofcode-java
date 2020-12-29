package adventofcode.calendar;

import adventofcode.utils.StringIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

public abstract class AbstractDay {
    public final int year;
    public final int day;

    public AbstractDay(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public String getInput() throws IOException {
        String path = String.format("adventofcode/inputs/year%d/Day%d.txt", year, day);
        URL url = ClassLoader.getSystemResource(path);
        if (url == null) {
            throw new FileNotFoundException("Resource not found at: " + path);
        }
        return StringIO.read(url);
    }

    public abstract void solve(String input, Consumer<Object> answers);

    public void run() throws IOException {
        String input = getInput();
        Printer printer = new Printer();
        printer.preSolve();
        solve(input, printer);
        printer.postSolve();
    }

    private static class Printer implements Consumer<Object> {
        private int partNumber;
        private long startTime;
        private long totalAnswerTime;

        public void preSolve() {
            partNumber = 0;
            startTime = System.nanoTime();
            totalAnswerTime = 0;
        }

        @Override
        public void accept(Object answer) {
            long answerTime = System.nanoTime() - startTime;
            totalAnswerTime += answerTime;
            partNumber += 1;
            printAnswer(answer, answerTime);
            startTime = System.nanoTime();
        }

        public void postSolve() {
            System.out.println();
            System.out.println("Total time taken: " + String.format("%.03fms", totalAnswerTime * 1e-6));
        }

        private void printAnswer(Object answer, long nanoTime) {
            String answerString = answer == null ? "No answer" : answer.toString();
            String[] lines = answerString.split("\n");
            for (int i = 0; i < lines.length; i++) {
                if (i == 0) {
                    System.out.printf("Part %d: ", partNumber);
                } else {
                    System.out.print("        ");
                }
                System.out.print(lines[i]);
                if (i == lines.length - 1) {
                    System.out.printf(" [%.03f ms]", nanoTime * 1e-6);
                }
                System.out.println();
            }
        }
    };

    public static void main(String[] args) throws Exception {
        Class<?> mainClass = Class.forName(System.getProperty("sun.java.command"));
        AbstractDay day = (AbstractDay) mainClass.getDeclaredConstructor().newInstance();
        day.run();
    }
}
