package com.xinyin.common.sso.service;

public interface LDAPService {

    boolean authenticate(String userName, String password,String busiUrl);

    boolean authenticate_(String userName,String password,String busiUrl);

    boolean authenticate(String userName, String password);

}
