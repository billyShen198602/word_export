package com.xinyin.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Slf4j
@Component
public class DummySSLSocketFactory_ {

    //安全传输层协议
    private static final String PROTOCOL = "TLS";

    // JKS/PKCS12
    private static final String KEY_KEYSTORE_TYPE = "PKCS12";

    private SSLSocketFactory factory;

    public DummySSLSocketFactory_() {

        try {
            factory = getSocketFactory();
        } catch (final Exception ex) {
            log.error(ex.getMessage());
        }

    }

    public static SocketFactory getDefault() {

        return new DummySSLSocketFactory();

    }

    public SSLSocketFactory getSocketFactory() throws Exception {
        String cerPath = ResourceUtils.getFile("classpath:server.cer").getPath();
        SSLSocketFactory socketFactory = null;
        try (InputStream cerInputStream = new FileInputStream(new File(cerPath))) {
//            TrustManager[] trustManagers = getTrustManagers(cerInputStream);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
            //加载证书
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate ca = certificateFactory.generateCertificate(cerInputStream);
            keyStore.load(null, null);
            //设置公钥
            keyStore.setCertificateEntry("server", ca);
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//            SSLContext sslContext = getSslContext(trustManagers);
            SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
            sslContext.init(null, trustManagers, new SecureRandom());
            socketFactory = sslContext.getSocketFactory();
        }
        return socketFactory;
    }
}
