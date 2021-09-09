package adventofcode.utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

@FunctionalInterface
public interface Enumerable<T> extends Iterable<T> {
    static <T> Enumerable<T> empty() {
        return (action) -> {};
    }

    static <T> Enumerable<T> of(T[] array) {
        return of(Arrays.asList(array));
    }

    static <T> Enumerable<T> of(Iterable<T> iterable) {
        return new Enumerable<>() {
            @Override
            public void forEach(Consumer<? super T> action) {
                iterable.forEach(action);
            }

            @Override
            public Iterator<T> iterator() {
                return iterable.iterator();
            }

            @Override
            public Spliterator<T> spliterator() {
                return iterable.spliterator();
            }
        };
    }

    void forEach(Consumer<? super T> action);

    @Override
    default Iterator<T> iterator() {
        return toList().iterator();
    }

    @Override
    default Spliterator<T> spliterator() {
        return toList().spliterator();
    }

    default List<T> toList() {
        List<T> list = new ArrayList<>();
        forEach(list::add);
        return list;
    }

    default Set<T> toSet() {
        Set<T> set = new HashSet<>();
        forEach(set::add);
        return set;
    }

    default <A, R> R collect(Collector<? super T, A, R> collector) {
        A acc = collector.supplier().get();
        forEach((x) -> collector.accumulator().accept(acc, x));
        return collector.finisher().apply(acc);
    }

    default <U> U reduce(Function<? super T, ? extends U> f, BinaryOperator<U> op, U identity) {
        class Reducer implements Consumer<T> {
            U value = identity;

            @Override
            public void accept(T x) {
                value = op.apply(value, f.apply(x));
            }
        }
        Reducer reducer = new Reducer();
        forEach(reducer);
        return reducer.value;
    }

    default <U> U reduce(Function<? super T, ? extends U> f, BinaryOperator<U> op) {
        class Reducer implements Consumer<T> {
            U value = null;

            @Override
            public void accept(T x) {
                if (value == null) {
                    Objects.requireNonNull(f.apply(x));
                } else {
                    value = op.apply(value, Objects.requireNonNull(f.apply(x)));
                }
            }
        }
        Reducer reducer = new Reducer();
        forEach(reducer);
        return reducer.value;
    }

    default int reduceAsInt(ToIntFunction<? super T> f, IntBinaryOperator op, int identity) {
        class Reducer implements Consumer<T> {
            int value = identity;

            @Override
            public void accept(T x) {
                value = op.applyAsInt(value, f.applyAsInt(x));
            }
        }
        Reducer reducer = new Reducer();
        forEach(reducer);
        return reducer.value;
    }

    default <U extends Comparable<U>> U min(Function<? super T, ? extends U> f) {
        return reduce(f, (a, b) -> a == null || b.compareTo(a) < 0 ? b : a, null);
    }

    default <U extends Comparable<U>> U max(Function<? super T, ? extends U> f) {
        return reduce(f, (a, b) -> a == null || b.compareTo(a) > 0 ? b : a, null);
    }

    default <U extends Comparable<U>> T argMin(Function<? super T, ? extends U> f) {
        class Accumulator implements Consumer<T> {
            T minArg;
            U min;

            @Override
            public void accept(T x) {
                U y = f.apply(x);
                if (min == null || y.compareTo(min) < 0) {
                    minArg = x;
                    min = y;
                }
            }
        }
        Accumulator accumulator = new Accumulator();
        forEach(accumulator);
        return accumulator.minArg;
    }

    default <U extends Comparable<U>> T argMax(Function<? super T, ? extends U> f) {
        class Accumulator implements Consumer<T> {
            T maxArg;
            U max;

            @Override
            public void accept(T x) {
                U y = f.apply(x);
                if (max == null || y.compareTo(max) > 0) {
                    maxArg = x;
                    max = y;
                }
            }
        }
        Accumulator accumulator = new Accumulator();
        forEach(accumulator);
        return accumulator.maxArg;
    }

    default int sum(ToIntFunction<? super T> f) {
        return reduceAsInt(f, Integer::sum, 0);
    }

    default int product(ToIntFunction<? super T> f) {
        return reduceAsInt(f, (a, b) -> a * b, 1);
    }
}
