package org.example.webmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.LoginInfo;
import org.example.webmanagement.pojo.Result;
import org.example.webmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录，员工信息：{}",emp);
        LoginInfo loginInfo=empService.login(emp);
        if (loginInfo!=null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
