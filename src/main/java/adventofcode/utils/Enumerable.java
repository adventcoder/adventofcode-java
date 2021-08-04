package adventofcode.utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

@FunctionalInterface
public interface Enumerable<T> extends Iterable<T> {
    static <T> Enumerable<T> empty() {
        return (action) -> {};
    }

    static <T> Enumerable<T> of(Iterable<T> iterable) {
        return new Enumerable<T>() {
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

    default <U> U reduce(Function<? super T, ? extends U> f, U identity, BinaryOperator<U> op) {
        class Reducer implements Consumer<T> {
            U value = identity;

            @Override
            public void accept(T t) {
                value = op.apply(value, f.apply(t));
            }
        }
        Reducer reducer = new Reducer();
        forEach(reducer);
        return reducer.value;
    }

    default int reduceAsInt(ToIntFunction<? super T> f, int identity, IntBinaryOperator op) {
        class Reducer implements Consumer<T> {
            int value = identity;

            @Override
            public void accept(T t) {
                value = op.applyAsInt(value, f.applyAsInt(t));
            }
        }
        Reducer reducer = new Reducer();
        forEach(reducer);
        return reducer.value;
    }

    default <U extends Comparable<U>> U min(Function<? super T, ? extends U> f) {
        return reduce(f, null, (a, b) -> a == null || a.compareTo(b) > 0 ? b : a);
    }

    default <U extends Comparable<U>> U max(Function<? super T, ? extends U> f) {
        return reduce(f, null, (a, b) -> a == null || a.compareTo(b) < 0 ? b : a);
    }

    default <U extends Comparable<U>> T argMin(Function<? super T, ? extends U> f) {
        return reduce((x) -> new AbstractMap.SimpleEntry<>(x, f.apply(x)), null, (a, b) -> a == null || a.getValue().compareTo(b.getValue()) > 0 ? b : a).getKey();
    }

    default <U extends Comparable<U>> T argMax(Function<? super T, ? extends U> f) {
        return reduce((x) -> new AbstractMap.SimpleEntry<>(x, f.apply(x)), null, (a, b) -> a == null || a.getValue().compareTo(b.getValue()) > 0 ? b : a).getKey();
    }

    default int sum(ToIntFunction<? super T> f) {
        return reduceAsInt(f, 0, Integer::sum);
    }

    default int product(ToIntFunction<? super T> f) {
        return reduceAsInt(f, 1, (a, b) -> a * b);
    }
}
