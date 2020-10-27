package com.aps.controller;

import com.aps.bean.*;
import com.aps.solver.MOPlanningSolver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private MOPlanningSolver timeTableSolver = new MOPlanningSolver();
    @RequestMapping("/login")
    public String login(User user,ModelMap map) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            log.error(String.valueOf(usernamePasswordToken.getPassword()));
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.addAttribute("error", "账号或密码错误！");
            return "/loginPage";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            map.addAttribute("error", "没有权限!");
            return "/loginPage";
        }
        return "index";
    }
    //注解验角色和权限
    @RequestMapping("/index")
    public String index(ModelMap map) {
        MainDashboard mainDashboard = new MainDashboard();
        return "index";
    }

    @RequestMapping("/loginPage")
    public String loginPage(ModelMap map){
        map.addAttribute("error", "");
        return "/loginPage";
    }
}