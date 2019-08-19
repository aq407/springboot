package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
@SessionAttributes(types=User.class)
public class LoginController {
    @Autowired
    private LogonMapper logonMapper;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String dologin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model,
                          HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println(username);
       User users = logonMapper.findUser(username, password);


       if (users == null){
           String error = "账号或密码错误";
           model.addAttribute("error",error);
           model.addAttribute("user",users);
           return "login";
       }else {
           Integer Id = users.getId();
           System.out.println("登录成功");
           response.addCookie(new Cookie("cookie",Id.toString()));
           request.getSession().setAttribute("user",users);
           System.out.println( request.getSession().getId());
           request.getSession().setAttribute("userId",users.getId());
           return "redirect:/";
       }
    }
}
