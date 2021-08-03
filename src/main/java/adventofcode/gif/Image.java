package adventofcode.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Image extends Entry {
    public GraphicControlExtension controlExtension;

    public static Image read(InputStream in) throws IOException {
        return new Image();
    }

    @Override
    public void write(OutputStream out) throws IOException {
        
    }
}
