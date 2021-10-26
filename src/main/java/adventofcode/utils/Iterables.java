package adventofcode.utils;

import java.util.*;
import java.util.function.*;

public class Iterables {
    public static <T> T first(Iterable<T> xs) {
        for (T x : xs) {
            return x;
        }
        return null;
    }

    public static <T> T findFirst(Predicate<? super T> f, Iterable<T> xs) {
        for (T x : xs) {
            if (f.test(x)) {
                return x;
            }
        }
        return null;
    }

    public static <T, U> Iterable<U> map(Function<? super T, ? extends U> f, Iterable<T> xs) {
        return () -> {
            Iterator<T> it = xs.iterator();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public U next() {
                    return f.apply(it.next());
                }

                @Override
                public void remove() {
                    it.remove();
                }
            };
        };
    }

    public static <T, U> List<U> collect(Function<? super T, ? extends U> f, Iterable<T> xs) {
        List<U> list = new ArrayList<>();
        for (T x : xs) {
            list.add(f.apply(x));
        }
        return list;
    }

    public static <T, U extends Comparable<U>> U min(Function<? super T, ? extends U> f, Iterable<T> xs) {
        U xMin = null;
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            xMin = f.apply(it.next());
            do {
                U curr = f.apply(it.next());
                if (curr.compareTo(xMin) < 0) xMin = curr;
            } while (it.hasNext());
        }
        return xMin;
    }

    public static <T, U extends Comparable<U>> U max(Function<? super T, ? extends U> f, Iterable<T> xs) {
        U xMax = null;
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            xMax = f.apply(it.next());
            do {
                U x = f.apply(it.next());
                if (x.compareTo(xMax) > 0) xMax = x;
            } while (it.hasNext());
        }
        return xMax;
    }

    public static <T, U extends Comparable<U>> T argMin(Function<? super T, ? extends U> f, Iterable<T> xs) {
        T xMin = null;
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            xMin = it.next();
            U yMin = f.apply(xMin);
            do {
                T x = it.next();
                U y = f.apply(x);
                if (y.compareTo(yMin) < 0) {
                    xMin = x;
                    yMin = y;
                }
            } while (it.hasNext());
        }
        return xMin;
    }

    public static <T, U extends Comparable<U>> T argMax(Function<? super T, ? extends U> f, Iterable<T> xs) {
        T xMax = null;
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            xMax = it.next();
            U yMax = f.apply(xMax);
            do {
                T x = it.next();
                U y = f.apply(x);
                if (y.compareTo(yMax) > 0) {
                    xMax = x;
                    yMax = y;
                }
            } while (it.hasNext());
        }
        return xMax;
    }

    public static <T> boolean any(Predicate<? super T> f, Iterable<T> xs) {
        for (T x : xs) {
            if (f.test(x)) return true;
        }
        return false;
    }

    public static <T> boolean none(Predicate<? super T> f, Iterable<T> xs) {
        return !any(f, xs);
    }

    public static <T> boolean all(Predicate<? super T> f, Iterable<T> xs) {
        for (T x : xs) {
            if (!f.test(x)) return false;
        }
        return true;
    }

    public static <T> int sum(ToIntFunction<? super T> f, Iterable<T> xs) {
        int sum = 0;
        for (T x : xs) sum += f.applyAsInt(x);
        return sum;
    }

    public static <T> int product(ToIntFunction<? super T> f, Iterable<T> xs) {
        int product = 1;
        for (T x : xs) product *= f.applyAsInt(x);
        return product;
    }

    public static <T> int tally(Predicate<? super T> f, Iterable<T> xs) {
        int count = 0;
        for (T x : xs) {
            if (f.test(x)) {
                count++;
            }
        }
        return count;
    }
}
