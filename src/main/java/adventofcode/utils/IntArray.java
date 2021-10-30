package adventofcode.utils;

import java.util.function.LongBinaryOperator;

public class IntArray {
    public static int[] parse(String str, String prefix, String separator, String suffix) {
        if (prefix != null && str.startsWith(prefix)) {
            str = str.substring(prefix.length());
        }
        if (suffix != null && str.endsWith(suffix)) {
            str = str.substring(0, str.length() - suffix.length());
        }
        return parse(str, separator);
    }

    public static int[] parse(String str, String separator) {
        String[] tokens = str.split(separator);
        int[] array = new int[tokens.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        return array;
    }

    public static Long reduce(LongBinaryOperator op, long[] array) {
        if (array.length == 0) return null;
        long acc = array[0];
        for (int i = 1; i < array.length; i++) {
            acc = op.applyAsLong(acc, array[i]);
        }
        return acc;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void reverse(int[] array, int start, int end) {
        while (end - start > 1) {
            swap(array, start++, --end);
        }
    }

    public static void rotateLeft(int[] array, int n) {
        n = Math.floorMod(n, array.length);
        reverse(array, 0, n);
        reverse(array, n, array.length);
        reverse(array, 0, array.length);
    }

    public static void rotateRight(int[] array, int n) {
        rotateLeft(array, -n);
    }

    public static boolean nextPermutation(int[] array) {
        for (int size = array.length - 1; size > 0; size--) {
            for (int i = array.length - 1; i >= size; i--) {
                if (array[i] > array[size - 1]) {
                    swap(array, i, size - 1);
                    reverse(array, size, array.length);
                    return true;
                }
            }
        }
        return false;
    }
}
