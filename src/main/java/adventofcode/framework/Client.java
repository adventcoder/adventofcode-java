package adventofcode.framework;

import adventofcode.utils.StringIO;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Properties;

public class Client {
    public static Client create(Properties properties) {
        try {
            return new Client(getSSLContext(properties), Cache.create(properties));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private static SSLContext getSSLContext(Properties properties) throws GeneralSecurityException {
        if (Boolean.parseBoolean(properties.getProperty("insecure"))) {
            return getInsecureSSLContext();
        } else {
            return SSLContext.getDefault();
        }
    }

    private static SSLContext getInsecureSSLContext() throws GeneralSecurityException {
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

    private final SSLContext sslContext;
    private final Cache cache;

    public Client(SSLContext sslContext, Cache cache) {
        this.sslContext = sslContext;
        this.cache = cache;
    }

    public String getInput(String session, int year, int day) throws IOException {
        return cache.computeIfAbsent(session, "input-" + year + "-" + day + ".txt", () -> {
            try (InputStream in = getInputStream(session, "/" + year + "/day/" + day + "/input")) {
                return StringIO.read(in);
            }
        });
    }

    private InputStream getInputStream(String session, String path) throws IOException {
        URL url = new URL("https://adventofcode.com" + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", "session=" + session);
        if (sslContext != null && conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(sslContext.getSocketFactory());
        }
        if (conn.getResponseCode() == 200) {
            return conn.getInputStream();
        } else {
            throw new IOException(buildErrorMessage(conn));
        }
    }

    private String buildErrorMessage(HttpURLConnection conn) {
        StringBuilder errorMessage = new StringBuilder("Error response from server");
        try {
            errorMessage.append(", status: ").append(conn.getResponseCode()).append(" ").append(conn.getResponseMessage());
            if (conn.getErrorStream() != null) {
                try (InputStream in = conn.getErrorStream()) {
                    errorMessage.append(", body: ").append(StringIO.read(in));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return errorMessage.toString();
    }
}
