package adventofcode.utils.gif;

import java.io.IOException;
import java.io.OutputStream;

public class DataBlockOutputStream extends OutputStream {
    private final OutputStream out;
    private final byte[] block = new byte[255];
    private int blockLength;

    public DataBlockOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        block[blockLength++] = (byte) b;
        if (blockLength == 255) {
            flush();
        }
    }

    @Override
    public void write(byte[] b, int off, int length) throws IOException {
        while (blockLength + length >= 255) {
            int space = 255 - blockLength;
            System.arraycopy(b, off, block, blockLength, space);
            off += space;
            length -= space;
            flush();
        }
        System.arraycopy(b, off, block, blockLength, length);
        blockLength += length;
    }

    @Override
    public void flush() throws IOException {
        if (blockLength > 0) {
            out.write(blockLength);
            out.write(block, 0, blockLength);
            blockLength = 0;
        }
    }

    @Override
    public void close() throws IOException {
        flush();
        out.write(0);
    }
}
