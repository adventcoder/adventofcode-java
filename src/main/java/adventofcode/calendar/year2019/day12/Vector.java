package adventofcode.calendar.year2019.day12;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Vector {
    public static final String[] axes = { "x", "y", "z" };

    public static int[] zero() {
        return new int[axes.length];
    }

    public static int[] parse(String string) {
        if (!string.startsWith("<") || !string.endsWith(">")) {
            throw new InputMismatchException(string);
        }
        int[] vector = new int[axes.length];
        for (String part : string.substring(1, string.length() - 1).split(",")) {
            String[] pair = part.split("=", 2);
            int i = Arrays.asList(axes).indexOf(pair[0].trim());
            if (i >= 0) {
                vector[i] = Integer.parseInt(pair[1].trim());
            }
        }
        return vector;
    }

    public static String format(int[] vector) {
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(axes[i]);
            builder.append("=");
            builder.append(vector[i]);
        }
        builder.append(">");
        return builder.toString();
    }
}
