package adventofcode.utils;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Matcher<T> implements Iterator<T> {
    private final Supplier<? extends T> source;
    private T next;

    public Matcher(Supplier<T> source) {
        this.source = source;
        this.next = source.get();
    }

    public Matcher(Iterator<T> iterator) {
        this(() -> iterator.hasNext() ? Objects.requireNonNull(iterator.next()) : null);
    }

    public static <T> Matcher<T> of(Iterable<T> iterable) {
        return new Matcher<>(iterable.iterator());
    }

    public static <T> Matcher<T> of(T[] array) {
        return of(Arrays.asList(array));
    }

    public T peek() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public T next() {
        if (next == null) throw new NoSuchElementException();
        T curr = next;
        next = source.get();
        return curr;
    }

    public boolean hasNext(Predicate<? super T> pred) {
        return next != null && pred.test(next);
    }

    public boolean hasNext(T target) {
        return hasNext(target::equals);
    }

    public boolean hasNext(Collection<? extends T> targets) {
        return hasNext(targets::contains);
    }

    public boolean tryNext(Predicate<? super T> pred) {
        if (!hasNext(pred)) return false;
        next = source.get();
        return true;
    }

    public boolean tryNext(T target) {
        return tryNext(target::equals);
    }

    public boolean tryNext(Collection<? extends T> targets) {
        return tryNext(targets::contains);
    }

    public T next(Predicate<? super T> pred) {
        if (next == null) throw new NoSuchElementException();
        if (!pred.test(next)) throw new InputMismatchException("next: " + next);
        T curr = next;
        next = source.get();
        return curr;
    }

    public T next(T target) {
        return next(target::equals);
    }

    public T next(Collection<? extends T> targets) {
        return next(targets::contains);
    }

    public static class OfCharSequence extends Matcher<CharSequence> {
        public OfCharSequence(Supplier<CharSequence> source) {
            super(source);
        }

        public OfCharSequence(Iterator<CharSequence> iterator) {
            super(iterator);
        }

        private static Predicate<CharSequence> match(String regex) {
            return (seq) -> Pattern.compile(regex).matcher(seq).matches();
        }

        public boolean hasNextMatch(String regex) {
            return hasNext(match(regex));
        }

        public boolean tryNextMatch(String regex) {
            return tryNext(match(regex));
        }

        public String nextMatch(String regex) {
            return next(match(regex)).toString();
        }

        public String tryNextMatch(String regex, int group) {
            if (!hasNext()) return null;
            java.util.regex.Matcher matcher = Pattern.compile(regex).matcher(next());
            if (!matcher.matches()) return null;
            return matcher.group(group);
        }

        public String nextMatch(String regex, int group) {
            CharSequence next = next();
            java.util.regex.Matcher matcher = Pattern.compile(regex).matcher(next);
            if (!matcher.matches()) throw new InputMismatchException("next: " + next);
            return matcher.group(group);
        }
    }
}
