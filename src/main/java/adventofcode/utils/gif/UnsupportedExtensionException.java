package adventofcode.utils.gif;

import java.io.IOException;

public class UnsupportedExtensionException extends IOException {
    public final int label;

    public UnsupportedExtensionException(int label) {
        super("Unknown extension label: " + label);
        this.label = label;
    }
}
