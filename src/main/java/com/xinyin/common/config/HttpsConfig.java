package com.xinyin.common.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @author billy
 * http自动转向https
 */
@Slf4j
//@Configuration
public class HttpsConfig {

    //安全传输层协议
    private static final String PROTOCOL = "TLS";

    // JKS/PKCS12
    private static final String KEY_KEYSTORE_TYPE = "PKCS12";

//    @Value("${http.port}")
//    private Integer httpPort;
//
//    @Value("${server.port}")
//    private Integer httpsPort;


//    @Autowired
//    private Connector httpConnector;

//    @Bean
//    public SSLSocketFactory getSocketFactory() throws Exception {
//        String cerPath = ResourceUtils.getFile("classpath:server.cer").getPath();
//        SSLSocketFactory socketFactory = null;
//        try (InputStream cerInputStream = new FileInputStream(new File(cerPath))) {
////            TrustManager[] trustManagers = getTrustManagers(cerInputStream);
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
//            //加载证书
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            Certificate ca = certificateFactory.generateCertificate(cerInputStream);
//            keyStore.load(null, null);
//            //设置公钥
//            keyStore.setCertificateEntry("server", ca);
//            trustManagerFactory.init(keyStore);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
////            SSLContext sslContext = getSslContext(trustManagers);
//            SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
//            sslContext.init(null, trustManagers, new SecureRandom());
//            socketFactory = sslContext.getSocketFactory();
//        }
//        return socketFactory;
//    }

//    @Bean
//    public SSLContext getSslContext(TrustManager[] trustManagers) throws Exception {
//        SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
//        sslContext.init(null, trustManagers, new SecureRandom());
//        return sslContext;
//    }

//    @Bean
//    public TrustManager[] getTrustManagers(InputStream inputStream) throws Exception {
//        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
//        //加载证书
//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//        Certificate ca = certificateFactory.generateCertificate(inputStream);
//        keyStore.load(null, null);
//        //设置公钥
//        keyStore.setCertificateEntry("server", ca);
//        trustManagerFactory.init(keyStore);
//        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//        return trustManagers;
//    }

//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        if (httpPort == null){
//            return null;
//        }
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector);
//        return tomcat;
//    }
//
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(httpPort);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(httpsPort);
//        return connector;
//    }



}
