package adventofcode.utils.search;

public interface Traversable<A> {
    Iterable<A> actions();

    boolean tryApply(A action);

    default void apply(A action) {
        if (!tryApply(action)) {
            throw new UnsupportedOperationException();
        }
    }
}
