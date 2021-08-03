package adventofcode.utils.gif;

import java.io.IOException;
import java.io.OutputStream;

public class GraphicControlExtension extends Extension {
    public static final int label = 0x7F;

    @Override
    public int getLabel() {
        return label;
    }

    @Override
    public void writeBlocks(OutputStream out) throws IOException {

    }
}
