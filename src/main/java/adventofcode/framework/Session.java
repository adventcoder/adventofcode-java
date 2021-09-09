package adventofcode.framework;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Session {
    public static Session getInstance() throws IOException {
        return new Session(getProperties());
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        URL propertiesURL = ClassLoader.getSystemResource("session.properties");
        if (propertiesURL != null) {
            try (InputStream in = propertiesURL.openStream()) {
                properties.load(in);
            }
        }
        return properties;
    }

    private final String session;
    private final Client client;

    public Session(Properties properties) {
        this.session = Objects.requireNonNull(properties.getProperty("session"));
        this.client = Client.create(properties);
    }

    public String getInput(int year, int day) throws IOException {
        return client.getInput(session, year, day);
    }
}
