package adventofcode.utils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Functions {
    public static <T, U extends Comparable<U>> U min(Function<? super T, U> f, Collection<T> xs) {
        U yMin = null;
        for (T x : xs) {
            U y = f.apply(x);
            if (yMin == null || y.compareTo(yMin) < 0) {
                yMin = y;
            }
        }
        return yMin;
    }

    public static <T, U extends Comparable<U>> U max(Function<? super T, U> f, Collection<T> xs) {
        U yMax = null;
        for (T x : xs) {
            U y = f.apply(x);
            if (yMax == null || y.compareTo(yMax) > 0) {
                yMax = y;
            }
        }
        return yMax;
    }

    public static <T, U extends Comparable<U>> T argmin(Function<? super T, U> f, Collection<T> xs) {
        T xMin = null;
        U yMin = null;
        for (T x : xs) {
            U y = f.apply(x);
            if (yMin == null || y.compareTo(yMin) < 0) {
                xMin = x;
                yMin = y;
            }
        }
        return xMin;
    }

    public static <T, U extends Comparable<U>> T argmax(Function<? super T, U> f, Collection<T> xs) {
        T xMax = null;
        U yMax = null;
        for (T x : xs) {
            U y = f.apply(x);
            if (yMax == null || y.compareTo(yMax) > 0) {
                xMax = x;
                yMax = y;
            }
        }
        return xMax;
    }

    public static <T> int sum(ToIntFunction<? super T> f, Collection<T> xs) {
        int sum = 0;
        for (T x : xs) {
            sum += f.applyAsInt(x);
        }
        return sum;
    }

    public static <T> int product(ToIntFunction<? super T> f, Collection<T> xs) {
        int product = 1;
        for (T value : xs) {
            product *= f.applyAsInt(value);
        }
        return product;
    }
}
