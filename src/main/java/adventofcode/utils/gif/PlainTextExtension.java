package adventofcode.utils.gif;

import java.io.IOException;
import java.io.OutputStream;

public class PlainTextExtension extends GraphicExtension {
    public static final int label = 0x01;

    public GraphicControlExtension controlExtension;

    @Override
    public int getLabel() {
        return label;
    }

    @Override
    public void writeBlocks(OutputStream out) throws IOException {

    }
}
