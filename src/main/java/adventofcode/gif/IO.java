package adventofcode.gif;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class IO {
    public static int readByte(InputStream in) throws IOException{
        int b = in.read();
        if (b == -1) throw new EOFException();
        return b;
    }

    public static byte[] readBytes(InputStream in, int length) throws IOException {
        byte[] out = new byte[length];
        readBytes(in, out, 0, length);
        return out;
    }

    public static void readBytes(InputStream in, byte[] out) throws IOException {
        readBytes(in, out, 0, out.length);
    }

    public static void readBytes(InputStream in, byte[] out, int offset, int length) throws IOException {
        while (length > 0) {
            int bytesRead = in.read(out, offset, length);
            if (bytesRead == -1) throw new EOFException();
            offset += bytesRead;
            length -= bytesRead;
        }
    }
}
