package adventofcode.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class CommentExtension extends Extension {
    public static final int label = 0xFF;

    private final byte[] data;

    public CommentExtension(byte[] data) {
        this.data = Objects.requireNonNull(data);
    }

    @Override
    public int getLabel() {
        return label;
    }

    @Override
    public void writeBlocks(OutputStream out) throws IOException {
        writeDataBlocks(out, data);
    }

    public static CommentExtension read(InputStream in) throws IOException {
        return new CommentExtension(readDataBlocks(in));
    }
}
