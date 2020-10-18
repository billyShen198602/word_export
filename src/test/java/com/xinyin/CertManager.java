package com.xinyin;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CertManager extends TestCase {

    @Test
    public void testReadX509CerFile() throws Exception{
        try {
            // 读取证书文件
//            File file = new File("src/GYGSCB2100000500.cer");
            File file = new File("F:\\work\\citic\\uat_public_usoappl\\DER_Pub.cer");
//            File file = new File("F:\\work\\citic\\uat_public_usoappl\\Base64_Pub.cer");
            InputStream inStream = new FileInputStream(file);
            // 创建X509工厂类
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //CertificateFactory cf = CertificateFactory.getInstance("X509");
            // 创建证书对象
            X509Certificate oCert = (X509Certificate) cf
                    .generateCertificate(inStream);
            inStream.close();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            String info = null;
            // 获得证书版本
            info = String.valueOf(oCert.getVersion());
            log.info("证书版本:" + info);
            // 获得证书序列号
            info = oCert.getSerialNumber().toString(16);
            log.info("证书序列号:" + info);
            // 获得证书有效期
            Date beforedate = oCert.getNotBefore();
            info = dateformat.format(beforedate);
            log.info("证书生效日期:" + info);
            Date afterdate = oCert.getNotAfter();
            info = dateformat.format(afterdate);
            log.info("证书失效日期:" + info);
            // 获得证书主体信息
            info = oCert.getSubjectDN().getName();
            log.info("证书拥有者:" + info);
            // 获得证书颁发者信息
            info = oCert.getIssuerDN().getName();
            log.info("证书颁发者:" + info);
            // 获得证书签名算法名称
            info = oCert.getSigAlgName();
            log.info("证书签名算法:" + info);

        } catch (final Exception ex) {
            log.error("解析证书出错！" + ex.getMessage());
        }
    }

}
