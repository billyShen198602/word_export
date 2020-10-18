package com.xinyin;

import com.xinyin.common.sso.service.Impl.LDAPServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapTest {

//    @Autowired
//    private LdapContext ldapContext;
    @Autowired
    private LDAPServiceImpl ldapService;

    @Test
    public void testGetLdapContext() throws Exception{
//        log.info(ldapContext.toString());
//        boolean authenticate = ldapService.authenticate("cn=Manager,c=cn", "123456","www.baidu.com");
        boolean authenticate = ldapService.authenticate("manager", "123456","www.baidu.com");
        log.info("result:" + authenticate);
    }


}
