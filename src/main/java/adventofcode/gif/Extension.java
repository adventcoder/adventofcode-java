package adventofcode.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Extension extends Entry {
    public static Extension read(InputStream in) throws IOException {
        int label = IO.readByte(in);
        if (label == PlainTextExtension.label) {
            return PlainTextExtension.read(in);
        } else if (label == GraphicControlExtension.label) {
            return GraphicControlExtension.read(in);
        } else if (label == CommentExtension.label) {
            return CommentExtension.read(in);
        } else {
            throw new UnsupportedExtensionException(label);
        }
    }

    public void write(OutputStream out) throws IOException {
        out.write('!');
        out.write(getLabel());
        writeBlocks(out);
    }

    public abstract int getLabel();

    public abstract void writeBlocks(OutputStream out) throws IOException;

    public static byte[] readDataBlocks(InputStream in) throws IOException {
        try (DataBlockInputStream dataIn = new DataBlockInputStream(in)) {
            return dataIn.readAllBytes();
        }
    }

    public static void writeDataBlocks(OutputStream out, byte[] data) throws IOException {
        try (DataBlockOutputStream dataOut = new DataBlockOutputStream(out)) {
            dataOut.write(data);
        }
    }
}
