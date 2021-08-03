package adventofcode.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScreenDescriptor {
    public int width;
    public int height;
    public Palette palette;
    public int sourcePrimaryColorDepth;
    public int backgroundColorIndex;
    public Double aspectRatio;

    public static ScreenDescriptor read(InputStream in) throws IOException {
        byte[] block = IO.readBytes(in, 7);
        ScreenDescriptor sd = new ScreenDescriptor();
        sd.width = Bytes.getWord(block, 0);
        sd.height = Bytes.getWord(block, 2);
        sd.sourcePrimaryColorDepth = Bytes.getBits(block, 4, 4, 3);
        sd.backgroundColorIndex = Bytes.getByte(block, 5);
        if (block[6] != 0) {
            sd.aspectRatio = (Bytes.getByte(block, 6) + 15) / 64.0;
        }
        if (Bytes.getBit(block, 4, 7) == 1) {
            sd.palette = new Palette(Bytes.getBits(block, 4, 1, 3));
            if (Bytes.getBit(block, 4, 3) == 1) {
                sd.palette.sorted = true;
            }
            sd.palette.readColors(in);
        }
        return sd;
    }

    public void write(OutputStream out) throws IOException {
        byte[] block = new byte[7];
        Bytes.setWord(block, 0, width);
        Bytes.setWord(block, 2, height);
        Bytes.setBits(block, 4, 4, 3, sourcePrimaryColorDepth);
        Bytes.setByte(block, 5, backgroundColorIndex);
        if (aspectRatio != null) {
            Bytes.setByte(block, 6, (int) Math.floor(aspectRatio * 64.0) - 15);
        }
        if (palette != null) {
            Bytes.setBit(block, 4, 7);
            if (palette.sorted) {
                Bytes.setBit(block, 4, 4);
            }
            Bytes.setBits(block, 4, 0, 3, palette.depth);
        }
        out.write(block);
        if (palette != null) {
            palette.writeColors(out);
        }
    }
}
