package com.xinyin.common.sso.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xinyin.common.sso.User;
import com.xinyin.common.sso.service.TokenService;
import org.springframework.stereotype.Service;


/**
 * @author billy
 */
@Service("TokenService")
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token = JWT.create().withAudience(String.valueOf(user.getId()))// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
