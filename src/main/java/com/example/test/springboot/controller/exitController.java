package com.example.test.springboot.controller;

import com.example.test.springboot.moder.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class exitController {
    @GetMapping("/exit")
    public String exit(HttpServletRequest request, HttpServletResponse response){

        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userId");
        Cookie cookie = new Cookie("cookie",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);


        return "redirect:index";

    }

}
