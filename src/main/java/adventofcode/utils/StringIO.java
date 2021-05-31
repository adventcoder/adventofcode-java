package adventofcode.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StringIO {
    public static String read(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            lines.add(line);
        }
        return String.join("\n", lines);
    }

    public static void write(OutputStream out, String string) throws IOException {
        Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        for (String line : string.split("\n")) {
            writer.write(line);
            writer.write(System.lineSeparator());
        }
        writer.flush();
    }
}
