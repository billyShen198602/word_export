package com.xinyin.common.sso;

import com.xinyin.common.sso.service.LDAPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserServiceController {

    @Autowired
    private LDAPService ldapService;

//    @PostMapping(value = "/login")
//    public Result login(String username, String password, String url, HttpServletResponse response){
//       return null;
//
//
//    }
}
