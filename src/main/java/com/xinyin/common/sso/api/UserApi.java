package com.xinyin.common.sso.api;

import com.xinyin.common.annotation.UserLoginToken;
import com.xinyin.common.domain.Audience;
import com.xinyin.common.model.MyException;
import com.xinyin.common.model.Result;
import com.xinyin.common.sso.User;
import com.xinyin.common.sso.service.LDAPService;
import com.xinyin.common.sso.service.TokenService;
import com.xinyin.common.sso.service.UserService;
import com.xinyin.common.util.JwtTokenUtil;
import com.xinyin.common.util.ResultEnum;
import com.xinyin.common.util.ResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author billy
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserApi {

    @Autowired
    private Audience audience;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LDAPService ldapService;

    @ApiOperation(value = "CRM用户注册接口",notes = "CRM用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "name",dataType = "String",required = true,value = "用户注册名"),
    })
    @PostMapping("/register")
    public Result register(@RequestParam(value = "name") String name){
        User user = userService.register(name);
        return ResultUtils.success(user);
    }

    @ApiOperation(value = "CRM用户登录接口",notes = "CRM用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "username",dataType = "String",required = true,value = "用户名"),
            @ApiImplicitParam(paramType = "query",name = "password",dataType = "String",required = true,value = "密码"),
            @ApiImplicitParam(paramType = "query",name = "busiUrl",dataType = "String",required = true,value = "CRM重定向路径"),
    })
    @PostMapping("/login")
    public Result login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "busiUrl") String busiUrl,
                        HttpServletResponse response){
        //1.用户是否已经注册
        User user = userService.findByUsername(username);
        if (user == null){
            log.error("**********用户"+ username + "未注册!!!********************");
            return ResultUtils.error(ResultEnum.USER_NOT_REGISTER_ERROR.getCode(),
                    ResultEnum.USER_NOT_REGISTER_ERROR.getDesc());
        }
        //2.认证
        boolean authenticate = ldapService.authenticate(username, password, busiUrl);
        if (!authenticate) {
            throw new MyException(ResultEnum.USO_AUTH_ERROR.getCode(),
                    ResultEnum.USO_AUTH_ERROR.getDesc());
        }
        log.info("USO系统认证成功......用户：" + username + "登录成功......");
        //3.更新数据库user表password
        user = userService.updatePasswdByUsername(username,password);
        //4、生成token
//        user.setPassword(password);
        String token = tokenService.getToken(user);

//        String token = null;
//        try {
//            token = JwtTokenUtil.createJWT(username, "admin", audience);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        Map<String, String> map = new HashMap<>();
        map.put(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        //login成功后，重定向到index.html
        try {
            response.sendRedirect("/static/index.html");
        } catch (final IOException ex) {
            log.error("^^^^^^^^^^^^^^^^^^^^^^^^编译时异常：" + ex.getMessage() + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
        return ResultUtils.success();
    }

//    @PostMapping("/login")
//    public Object login(@RequestBody User user) {
//        JSONObject jsonObject = new JSONObject();
//        User userForBase = userService.findByUsername(user.getUsername());
//        try {
//            if (userForBase == null) {
//                jsonObject.put("message", "登录失败,用户不存在");
//                return jsonObject;
//            } else {
//                if (!userForBase.getPassword().equals(user.getPassword())) {
//                    jsonObject.put("message", "登录失败,密码错误");
//                    return jsonObject;
//                } else {
//                    String token = tokenService.getToken(userForBase);
//                    jsonObject.put("token", token);
//                    jsonObject.put("user", userForBase);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
