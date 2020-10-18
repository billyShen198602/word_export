package com.xinyin.common.sso.service.Impl;

import com.xinyin.common.sso.service.SSLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLSocketFactory;

@Slf4j
@Service
public class SSLServiceImpl implements SSLService {

    @Autowired
    private SSLSocketFactory sslSocketFactory;

    @Override
    public String sslAuth(String url) {

        return null;
    }
}
