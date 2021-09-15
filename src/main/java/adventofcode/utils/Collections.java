package adventofcode.utils;

import java.util.*;

public class Collections {
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(Object... entries) {
        return (List<T>) new ArrayList<>(Arrays.asList(entries));
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(Object... entries) {
        return (Set<T>) new HashSet<>(Arrays.asList(entries));
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Object... entries) {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            Object key = entries[i];
            Object value = i + 1 < entries.length ? entries[i + 1] : null;
            map.put(key, value);
        }
        return (Map<K, V>) map;
    }
}
