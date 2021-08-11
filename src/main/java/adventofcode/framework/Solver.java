package adventofcode.framework;

@FunctionalInterface
public interface Solver<T> {
    T solve(String input);
}
