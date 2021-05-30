package adventofcode;

import adventofcode.utils.StringIO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class Session {
    public static Session fromProperties(URL propertiesURL) throws IOException {
        Properties props = new Properties();
        try (InputStream in = propertiesURL.openStream()) {
            props.load(in);
        }
        return new Session(props);
    }

    private final String session;
    private final Cache cache;

    public Session(Properties props) {
        this.session = props.getProperty("session");
        this.cache = new Cache();
    }

    public String getInput(int year, int day) throws IOException {
        URL url = new URL("https://adventofcode.com/" + year + "/day/" + day + "/input");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", "session=" + session);
        if (conn.getResponseCode() == 200) {
            try (InputStream in = conn.getInputStream()) {
                return StringIO.read(in);
            }
        } else {
            throw new IOException("Server response not OK: " + conn.getResponseCode());
        }
    }

    public class Cache {
    }
}
