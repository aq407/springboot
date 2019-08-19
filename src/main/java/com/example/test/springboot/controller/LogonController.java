package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;
import java.net.URLConnection;

@Controller
public class LogonController {

    @Autowired
    private LogonMapper logonMapper;

    @GetMapping("/logon")
    public String logon() {
        return "logon";
    }

    @PostMapping("/logon")
    public String dologon(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("iconurl") String iconurl,
                          Model model) {
        User user = new User();
        //判断用户名和密码是否为空
        if ((username != null && username !="") && (password != null && password !="")){
            user.setUsername(username);
            user.setPassword(password);

            //如果url为空则设置默认url
            if (iconurl == null || iconurl == ""){
                user.setIconurl("http://static.hdslb.com/images/akari.jpg");
            }

            //判断url链接是否可用
            if (testUrlWithTimeOut(iconurl,2000) == true){
                user.setIconurl(iconurl);
            }else {
                String urlerror = "url错误";
                model.addAttribute("error",urlerror);
                return "logon";
            }
            user.setCreationTime(System.currentTimeMillis());
            System.out.println(user.getIconurl());
            logonMapper.create(user);
            return "login";
        }else {
            String error = "账号或密码格式错误";
            model.addAttribute("error",error);
            return "logon";
        }

    }

//判断url链接是否可用
    public static boolean testUrlWithTimeOut(String urlString,int timeOutMillSeconds){
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co =  url.openConnection();
            co.setConnectTimeout(timeOutMillSeconds);
            co.connect();
            System.out.println("连接可用");
            return true;
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
            return false;
        }
    }

}
