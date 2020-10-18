package com.xinyin.common.sso.service.Impl;

import com.xinyin.common.model.MyException;
import com.xinyin.common.domain.Audience;
import com.xinyin.common.sso.User;
import com.xinyin.common.sso.UserDao;
import com.xinyin.common.sso.service.LDAPService;
import com.xinyin.common.sso.service.UserService;
import com.xinyin.common.util.JwtTokenUtil;
import com.xinyin.common.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Audience audience;

    @Autowired
    private LDAPService ldapService;

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String,String> login(String username, String password, String busiUrl, HttpServletResponse response) {
        //1.认证
        boolean authenticate = ldapService.authenticate(username, password, busiUrl);
        if (!authenticate) {
            throw new MyException(ResultEnum.USO_AUTH_ERROR.getCode(),
                    ResultEnum.USO_AUTH_ERROR.getDesc());
        }
        log.info("USO系统认证成功......用户：" + username + "登录成功......");
        //2、生成token
        String token = null;
        try {
            token = JwtTokenUtil.createJWT(username, "admin", audience);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
//        com.xinyin.common.sso.mapper.UserMapper

        return map;
    }

    @Override
    public User register(String name) {
        User user = new User();
        user.setUsername(name);
        userDao.insertSelective(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        user = userDao.selectByUsername(user);
        return user;
    }

    @Override
    public User findByUserid(String userid) {
        User user = userDao.selectByPrimaryKey(Integer.parseInt(userid));
        return user;
    }

    @Override
    public User updatePasswdByUsername(String username,String passwd) {
//        userMapper.updatePasswdByUsername(username,passwd);
        User user = new User();
        user.setUsername(username);
        User user1 = userDao.selectByUsername(user);
        user1.setPassword(passwd);
        userDao.updateByPrimaryKey(user1);
        return user1;
    }
}
