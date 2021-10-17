package adventofcode.framework;

import java.io.IOException;

public abstract class AbstractPart<T> implements Part<T> {
    public static void main(String[] args) throws ReflectiveOperationException, IOException {
        Class<?> mainClass = Class.forName(System.getProperty("sun.java.command"));
        Part.main(mainClass.asSubclass(Part.class), args);
    }

    @Override
    public int year() {
        String[] parts = getClass().getPackageName().split("\\.");
        if (parts.length < 2 || !parts[parts.length - 2].matches("year\\d+")) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(parts[parts.length - 2].substring(4));
    }

    @Override
    public int day() {
        String[] parts = getClass().getPackageName().split("\\.");
        if (parts.length < 1 || !parts[parts.length - 1].matches("day\\d+")) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(parts[parts.length - 1].substring(3));
    }

    @Override
    public String getInput(Session session) throws IOException {
        return session.getInput(year(), day());
    }
}
