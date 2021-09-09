package adventofcode.framework;

import adventofcode.utils.IOSupplier;
import adventofcode.utils.StringIO;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Cache {
    public static Cache create(Properties properties) {
        return new Cache(getRootDir(properties));
    }

    private static File getRootDir(Properties properties) {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    private final File rootDir;

    public Cache(File rootDir) {
        this.rootDir = rootDir;
    }

    public String computeIfAbsent(String session, String name, IOSupplier<String> supplier) throws IOException {
        File dir = getSessionDir(session);
        File file = new File(dir, name);
        if (file.exists()) {
            return StringIO.read(file);
        } else {
            String result = supplier.get();
            if (dir.exists() || dir.mkdir()) {
                try {
                    StringIO.write(file, result);
                } catch (IOException ioe) {
                    if (file.exists() && !file.delete()) {
                        file.deleteOnExit();
                        System.exit(1);
                    }
                }
            }
            return result;
        }
    }

    private File getSessionDir(String session) {
        return new File(rootDir, "session-" + session);
    }
}
