package com.xinyin.common.sso.service.Impl;

import com.xinyin.common.sso.service.LDAPService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Slf4j
@Component
public class LDAPServiceImpl implements LDAPService {

    private static final String SECURITY_PRINCIPAL_PREFIX = "cn=";

    private static final String SECURITY_PRINCIPAL_SUFFIX = ",c=cn";

    @Value("${spring.ldap.urls}")
    private String LDAP_URL;

    /**
     * LDAP验证用户登录
     * @param userName
     * @param password
     * @return
     */
    public boolean authenticate(String userName, String password,String busiUrl) {
        boolean bRtn = false;// 标注是否验证成功，初始为false
        Hashtable<String, String> env = new Hashtable<String, String>(4);
        // LDAP 服务器的 URL 地址，
//        LDAP_URL = "ldap://xxx.xxx.xxx/dc=aaa,dc=bbb";
//        LDAP_URL = "ldap://47.93.199.61:389";
        //env 中的key都是固定值在 javax.naming.Context 类中
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");// ldapCF
        env.put(Context.PROVIDER_URL, LDAP_URL);// ldapURL
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // ldapAuthMode
        //username和对应的password怎么在LDAP服务器中设置，我也不知道
        //通过默认的用户名"cn=manager,dc=aaa,dc=bbb"(aaa、bbb的具体值要在配置文件中配置，具体看参考博文)和密码"secret",可以测试连接是否成功
        env.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL_PREFIX + userName + SECURITY_PRINCIPAL_SUFFIX);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.DNS_URL,busiUrl);
        DirContext ctx = null;
        try {
            //这条代码执行成功就是验证通过了，至于为什么我也不知道
            ctx = new InitialDirContext(env);
            bRtn = true;
            log.info("Ldap验证通过!");
        } catch (Exception ex) {
            log.error("Ldap 初始化 出错:", ex);
        } finally {
            try {
                if (ctx != null) {
                    ctx.close();
                    ctx = null;
                }
                env.clear();
            } catch (Exception e) {
                log.error("Ldap context close出错:", e);
            }
        }
        if (StringUtils.isBlank(LDAP_URL)) {
            bRtn = true;
        }
        //验证成功返回 true，验证失败返回false
        return bRtn;
    }

    @Override
    public boolean authenticate_(String userName, String password, String busiUrl) {
        boolean bRtn = false;// 标注是否验证成功，初始为false
        Hashtable<String, String> env = new Hashtable<String, String>(4);
        //env 中的key都是固定值在 javax.naming.Context 类中
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");// ldapCF
        env.put(Context.PROVIDER_URL, LDAP_URL);// ldapURL
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // ldapAuthMode
        //username和对应的password怎么在LDAP服务器中设置，我也不知道
        //通过默认的用户名"cn=manager,dc=aaa,dc=bbb"(aaa、bbb的具体值要在配置文件中配置，具体看参考博文)和密码"secret",可以测试连接是否成功
        env.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL_PREFIX + userName + SECURITY_PRINCIPAL_SUFFIX);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.DNS_URL,busiUrl);
        env.put(Context.REFERRAL,"ignore");
        env.put(Context.SECURITY_PROTOCOL,"ssl");
        env.put("java.naming.ldap.factory.socket", "com.xinyin.common.util.DummySSLSocketFactory_");
        DirContext ctx = null;
        try {
            //这条代码执行成功就是验证通过了，至于为什么我也不知道
            ctx = new InitialDirContext(env);
            bRtn = true;
            log.info("Ldap验证通过!");
        } catch (Exception ex) {
            log.error("Ldap 初始化 出错:", ex);
        } finally {
            try {
                if (ctx != null) {
                    ctx.close();
                    ctx = null;
                }
                env.clear();
            } catch (Exception e) {
                log.error("Ldap context close出错:", e);
            }
        }
        if (StringUtils.isBlank(LDAP_URL)) {
            bRtn = true;
        }
        //验证成功返回 true，验证失败返回false
        return bRtn;
    }

    @Override
    public boolean authenticate(String userName, String password) {
        boolean bRtn = false;// 标注是否验证成功，初始为false
        Hashtable<String, String> env = new Hashtable<String, String>(4);
        // LDAP 服务器的 URL 地址，
//        LDAP_URL = "ldap://xxx.xxx.xxx/dc=aaa,dc=bbb";
//        LDAP_URL = "ldap://47.93.199.61:389";
        //env 中的key都是固定值在 javax.naming.Context 类中
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");// ldapCF
        env.put(Context.PROVIDER_URL, LDAP_URL);// ldapURL
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // ldapAuthMode
        //username和对应的password怎么在LDAP服务器中设置，我也不知道
        //通过默认的用户名"cn=manager,dc=aaa,dc=bbb"(aaa、bbb的具体值要在配置文件中配置，具体看参考博文)和密码"secret",可以测试连接是否成功
        env.put(Context.SECURITY_PRINCIPAL, userName);
        env.put(Context.SECURITY_CREDENTIALS, password);
        DirContext ctx = null;
        try {
            //这条代码执行成功就是验证通过了，至于为什么我也不知道
            ctx = new InitialDirContext(env);
            bRtn = true;
            log.info("Ldap验证通过!");
        } catch (Exception ex) {
            log.error("Ldap 初始化 出错:", ex);
        } finally {
            try {
                if (ctx != null) {
                    ctx.close();
                    ctx = null;
                }
                env.clear();
            } catch (Exception e) {
                log.error("Ldap context close出错:", e);
            }
        }
        if (StringUtils.isBlank(LDAP_URL)) {
            bRtn = true;
        }
        //验证成功返回 true，验证失败返回false
        return bRtn;
    }


}
