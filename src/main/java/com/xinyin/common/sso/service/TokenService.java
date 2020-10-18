package com.xinyin.common.sso.service;

import com.xinyin.common.sso.User;

public interface TokenService {

    String getToken(User user);
}
