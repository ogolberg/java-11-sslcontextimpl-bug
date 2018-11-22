package jdk.test;

import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class TlsHelper {
    public static final String PASSWORD = "testtest";
    public static final String TLS_VERSION = "TLSv1.2";

    public static KeyStore loadKeyStore() throws Exception {
        final KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("keystore"), PASSWORD.toCharArray());

        return keyStore;
    }

    public static SSLContext clientTlsContext() throws Exception {
        final SSLContext clientContext = SSLContext.getInstance(TLS_VERSION);
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(loadKeyStore());

        clientContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

        return clientContext;
    }

    public static SSLContext serverTlsContext() throws Exception {
        final KeyStore keyStore = loadKeyStore();

        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, PASSWORD.toCharArray());

        final SSLContext serverContext = SSLContext.getInstance(TLS_VERSION);
        serverContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

        return serverContext;
    }
}
