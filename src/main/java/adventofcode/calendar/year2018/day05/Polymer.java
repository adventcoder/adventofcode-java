package adventofcode.calendar.year2018.day05;

public class Polymer {
    public static void react(StringBuilder polymer) {
        int newLength = 0;
        for (int i = 0; i < polymer.length(); i++) {
            char c = polymer.charAt(i);
            if (newLength > 0 && reacts(c, polymer.charAt(newLength - 1))) {
                newLength--;
            } else {
                polymer.setCharAt(newLength++, c);
            }
        }
        polymer.setLength(newLength);
    }

    public static void delete(StringBuilder polymer, char c) {
        int newLength = 0;
        for (int i = 0; i < polymer.length(); i++) {
            if (!sameType(polymer.charAt(i), c)) {
                polymer.setCharAt(newLength++, polymer.charAt(i));
            }
        }
        polymer.setLength(newLength);
    }

    public static boolean reacts(char c1, char c2) {
        return c1 != c2 && sameType(c1, c2);
    }

    public static boolean sameType(char c1, char c2) {
        return Character.toUpperCase(c1) == Character.toUpperCase(c2);
    }
}
