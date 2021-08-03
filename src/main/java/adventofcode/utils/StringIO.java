package adventofcode.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StringIO {
    public static String read(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            return read(in);
        }
    }

    public static void write(File file, String string) throws IOException {
        try (OutputStream out = new FileOutputStream(file)) {
            write(out, string);
        }
    }

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
