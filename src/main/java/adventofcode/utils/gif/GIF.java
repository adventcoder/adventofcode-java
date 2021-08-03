package adventofcode.utils.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GIF {
    public final ScreenDescriptor screenDescriptor;
    public final List<Entry> entries = new ArrayList<>();

    public GIF(ScreenDescriptor screenDescriptor) {
        this.screenDescriptor = Objects.requireNonNull(screenDescriptor);
    }

    public static GIF read(InputStream in) throws IOException {
        String header = readHeader(in);
        if (!header.equals("GIF89a")) {
            throw new IOException("Unsupported header: " + header);
        }
        GIF gif = new GIF(ScreenDescriptor.read(in));
        while (true) {
            try {
                Entry entry = Entry.read(in);
                if (entry == null) {
                    break;
                }
                gif.entries.add(entry);
            } catch (UnsupportedExtensionException e) {
                continue;
            }
        }
        return gif;
    }

    public static String readHeader(InputStream in) throws IOException {
        return new String(IO.readBytes(in, 6), StandardCharsets.US_ASCII);
    }

    public void write(OutputStream out) throws IOException {
        writeHeader(out);
        screenDescriptor.write(out);
        for (Entry entry : entries) {
            entry.write(out);
        }
    }

    private void writeHeader(OutputStream out) throws IOException {
        out.write("GIF89a".getBytes(StandardCharsets.US_ASCII));
    }
}
