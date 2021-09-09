package adventofcode.utils;

public class IntArray {
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void reverse(int[] array, int start, int end) {
        end--;
        while (start < end) {
            swap(array, start++, end--);
        }
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
