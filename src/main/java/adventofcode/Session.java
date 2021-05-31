package adventofcode;

import adventofcode.utils.SSL;
import adventofcode.utils.StringIO;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Session {
    public static Session fromProperties() throws IOException {
        Properties props = new Properties();
        URL propsURL = ClassLoader.getSystemResource("session.properties");
        if (propsURL != null) {
            try (InputStream in = propsURL.openStream()) {
                props.load(in);
            }
        }
        return new Session(props);
    }

    private final String session;
    private final boolean insecure;

    public Session(Properties props) {
        this.session = Objects.requireNonNull(props.getProperty("session"));
        this.insecure = Boolean.parseBoolean(props.getProperty("insecure"));
    }

    public String getInput(int year, int day) throws IOException {
        File sessionDir = new File(System.getProperty("java.io.tmpdir"), "session-" + session);
        if (!sessionDir.exists() && !sessionDir.mkdir()) {
             return getInputFromServer(year, day);
        }
        File inputFile = new File(sessionDir, "input-" + year + "-" + day + ".txt");
        if (inputFile.exists()) {
            try (InputStream in = new FileInputStream(inputFile)) {
                return StringIO.read(in);
            }
        } else {
            String input = getInputFromServer(year, day);
            try {
                try (OutputStream out = new FileOutputStream(inputFile)) {
                    StringIO.write(out, input);
                }
            } catch (IOException ioe) {
                inputFile.delete();
            }
            return input;
        }
    }

    private String getInputFromServer(int year, int day) throws IOException {
        URL url = new URL("https://adventofcode.com/" + year + "/day/" + day + "/input");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (session != null) {
            conn.setRequestProperty("Cookie", "session=" + session);
        }
        if (conn instanceof HttpsURLConnection && insecure) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(SSL.insecureContext.getSocketFactory());
        }
        if (conn.getResponseCode() == 200) {
            try (InputStream in = conn.getInputStream()) {
                return StringIO.read(in);
            }
        } else {
            throw new IOException("Server response not OK: " + conn.getResponseCode());
        }
    }
}
