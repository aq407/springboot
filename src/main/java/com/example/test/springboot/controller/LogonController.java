package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println(username);
        User user = new User();
        if ((username != null && username !="") && (password != null && password !="")){
            user.setUsername(username);
            user.setPassword(password);
            if (iconurl == null || iconurl == ""){
                user.setIconurl("http://static.hdslb.com/images/akari.jpg");
            }else {
                user.setIconurl(iconurl);
            }
            user.setCreationTime(System.currentTimeMillis());
            System.out.println(user.getIconurl());
            logonMapper.create(user);
            return "login";
        }else {
            String error = "账号或密码错误";
            model.addAttribute("error",error);
            return "logon";
        }

    }

}
