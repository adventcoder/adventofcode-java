package adventofcode.framework;

import adventofcode.utils.StringIO;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.Properties;

public class Session {
    private static final String baseDomain = "https://adventofcode.com";
    private static final URL propertiesURL = ClassLoader.getSystemResource("session.properties");

    public static Session getInstance() throws IOException {
        Properties properties = new Properties();
        if (propertiesURL != null) {
            try (InputStream in = propertiesURL.openStream()) {
                properties.load(in);
            }
        }
        String session = properties.getProperty("session");
        boolean insecure = Boolean.parseBoolean(properties.getProperty("insecure"));
        return new Session(session, insecure);
    }

    private final String session;
    private final boolean insecure;
    private static SSLContext insecureSSLContext;

    public Session(String session, boolean insecure) {
        this.session = Objects.requireNonNull(session);
        this.insecure = insecure;
    }

    public String getInput(int year, int day) throws IOException {
        File sessionDir = new File(System.getProperty("java.io.tmpdir"), "session-" + session);
        if (!sessionDir.exists() && !sessionDir.mkdir()) {
             return getInputFromServer(year, day);
        }
        File inputFile = new File(sessionDir, "input-" + year + "-" + day + ".txt");
        if (inputFile.exists()) {
            return StringIO.read(inputFile);
        } else {
            String input = getInputFromServer(year, day);
            try {
                StringIO.write(inputFile, input);
            } catch (IOException ioe) {
                if (!inputFile.delete()) {
                    inputFile.deleteOnExit();
                    System.exit(1);
                }
            }
            return input;
        }
    }

    private String getInputFromServer(int year, int day) throws IOException {
        URL url = new URL(baseDomain + "/" + year + "/day/" + day + "/input");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", "session=" + session);
        if (insecure && conn instanceof HttpsURLConnection) {
            setInsecure((HttpsURLConnection) conn);
        }
        if (conn.getResponseCode() == 200) {
            try (InputStream in = conn.getInputStream()) {
                return StringIO.read(in);
            }
        } else {
            String errorMessage = null;
            if (conn.getErrorStream() != null) {
                try (InputStream in = conn.getErrorStream()) {
                    errorMessage = StringIO.read(in);
                }
            }
            throw new IOException("Error response from server, status: " + conn.getResponseCode() + " " + conn.getResponseMessage() + (errorMessage == null ? "" : ", body: " + errorMessage));
        }
    }

    public void submitAnswer(int year, int day, String answer) throws IOException {
        throw new UnsupportedOperationException();
    }

    private void setInsecure(HttpsURLConnection conn) throws IOException {
        try {
            conn.setSSLSocketFactory(getInsecureSSLContext().getSocketFactory());
        } catch (GeneralSecurityException e) {
            throw new IOException("Could not make insecure connection", e);
        }
    }

    private static SSLContext getInsecureSSLContext() throws GeneralSecurityException {
        if (insecureSSLContext == null) {
            insecureSSLContext = createInsecureSSLContext();
        }
        return insecureSSLContext;
    }

    private static SSLContext createInsecureSSLContext() throws GeneralSecurityException {
        TrustManager insecureTrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[] { insecureTrustManager }, new SecureRandom());
        return context;
    }
}
