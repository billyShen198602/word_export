package com.xinyin.common.util;

import org.springframework.stereotype.Component;

import java.security.cert.*;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;

@Component
public class DummyTrustManager implements X509TrustManager {


    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
