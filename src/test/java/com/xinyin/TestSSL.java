package com.xinyin;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSSL {

    //安全传输层协议
    private static final String PROTOCOL = "TLS";

    // JKS/PKCS12
    private static final String KEY_KEYSTORE_TYPE = "PKCS12";

    private static SSLSocketFactory getSocketFactory(String cerPath) throws Exception {
        SSLSocketFactory socketFactory = null;
        try (InputStream cerInputStream = new FileInputStream(new File(cerPath))) {
            TrustManager[] trustManagers = getTrustManagers(cerInputStream);
            SSLContext sslContext = getSslContext(trustManagers);
            socketFactory = sslContext.getSocketFactory();
        }
        return socketFactory;
    }

    private static SSLContext getSslContext(TrustManager[] trustManagers) throws Exception {
        SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
        sslContext.init(null, trustManagers, new SecureRandom());
        return sslContext;
    }

    private static TrustManager[] getTrustManagers(InputStream inputStream) throws Exception {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
        //加载证书
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate ca = certificateFactory.generateCertificate(inputStream);
        keyStore.load(null, null);
        //设置公钥
        keyStore.setCertificateEntry("server", ca);
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        return trustManagers;
    }

    public static void main(String[] args) throws Exception {
        //获取SSLSocketFactory
//        String certPath = "E:\\billy\\auth1\\server.cer";//服务端公钥
//        String certPath = "classpath:server.cer";//服务端公钥
        String certPath = ResourceUtils.getFile("classpath:server.cer").getPath();
        SSLSocketFactory socketFactory = getSocketFactory(certPath);
        //发送请求
//        String url = "https://127.0.0.1:7090/server/ssl";
        String url = "https://47.93.199.61:7090/server/ssl";
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.sslSocketFactory(socketFactory);
        //解决报错javax.net.ssl.SSLPeerUnverifiedException: Hostname 127.0.0.1 not verified
        clientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                log.info("主机:" + s);
                return true;
            }
        });
        OkHttpClient client = clientBuilder.build();

        Request.Builder builder = new Request.Builder().url(url);
        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        //打印请求结果
        log.info(result);
    }

}
