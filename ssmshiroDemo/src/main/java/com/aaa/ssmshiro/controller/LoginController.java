package com.aaa.ssmshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * className:LoginController
 * discriptoin:
 * author:邢博
 * createTime:2018-11-15 22:05
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password ){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    /**
     * 判断是否有该角色
     */
    @ResponseBody
    @RequestMapping("/test1")
    public String test1(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.checkRole("admin");
        } catch (AuthorizationException e) {
            return "不拥有admin角色";
        }
        return "拥有admin角色";
    }


    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.checkRole("CEO");
        } catch (AuthorizationException e) {
            return "不拥有CEO角色";
        }
        return "拥有CEO角色";
    }

    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){

        return "login";
    }
}
