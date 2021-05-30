package adventofcode;

public abstract class Solver<T> {
    public abstract T solve(String input);

    public Answer<T> execute(String input) {
        long startTime = System.nanoTime();
        T answer = solve(input);
        long answerTime = System.nanoTime() - startTime;
        return new Answer<>(answer, answerTime);
    }
}
