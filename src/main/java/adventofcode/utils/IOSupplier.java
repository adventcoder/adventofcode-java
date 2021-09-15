package adventofcode.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Supplier;

@FunctionalInterface
public interface IOSupplier<T> {
    T get() throws IOException;

    static <T> Supplier<T> unchecked(IOSupplier<T> ioSupplier) {
        return () -> {
            try {
                return ioSupplier.get();
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        };
    }
}
