package adventofcode.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Palette {
    public final int depth;
    private final byte[] colors;
    public boolean sorted;

    public Palette(int depth) {
        if (depth < 1 || depth > 8) {
            throw new IllegalArgumentException("invalid color depth: " + depth);
        }
        this.depth = depth;
        colors = new byte[1 << depth];
    }

    public static Palette of(int... colors) {
        int depth = 0;
        while ((1 << depth) < colors.length) {
            depth++;
        }
        if ((1 << depth) > colors.length) {
            throw new IllegalArgumentException("size not a power of two");
        }
        Palette palette = new Palette(depth);
        for (int i = 0; i < colors.length; i++) {
            palette.setColor(i, colors[i]);
        }
        return palette;
    }

    public int getColor(int index) {
        return Bytes.getColor(colors, index * 3);
    }

    public void setColor(int index, int color) {
        Bytes.setColor(colors, index * 3, color);
    }

    public void writeColors(OutputStream out) throws IOException {
        out.write(colors);
    }

    public void readColors(InputStream in) throws IOException {
        IO.readBytes(in, colors);
    }
}
