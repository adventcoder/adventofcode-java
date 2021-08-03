package adventofcode.utils.gif;

import java.io.IOException;
import java.io.InputStream;

public class DataBlockInputStream extends InputStream {
    private final InputStream in;

    public DataBlockInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
