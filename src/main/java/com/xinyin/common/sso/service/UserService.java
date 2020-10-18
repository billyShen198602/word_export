package com.xinyin.common.sso.service;

import com.xinyin.common.sso.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {

    Map<String,String> login(String username, String password, String busiUrl, HttpServletResponse response);

    User register(String name);

    User findByUsername(String username);

    User findByUserid(String userid);

    User updatePasswdByUsername(String username,String passwd);
}
