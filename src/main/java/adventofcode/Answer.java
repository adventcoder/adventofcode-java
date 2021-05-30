package adventofcode;

public class Answer<T> {
    private final T value;
    private final long time;

    public Answer(T value, long time) {
        this.value = value;
        this.time = time;
    }

    public void print(String prefix) {
        String[] lines = value.toString().split("\n");
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

    private static String formatTime(long nanos) {
        return String.format("%d ms", nanos / 1000);
    }
}
