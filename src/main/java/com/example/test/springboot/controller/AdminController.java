package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class AdminController {
    @Autowired
    private LogonMapper logonMapper;

    @RequestMapping("/admin")
    public String listAll(Model model){
        List<User> cs = logonMapper.findAll();
            model.addAttribute("us",cs);
        return "admin";
    }

    @PostMapping("/updata")
    public String updata(@RequestParam("uid") Integer uid,
                         @RequestParam("uname") String uname,
                         @RequestParam("upwd") String upwd
                         ){
        logonMapper.update(uname,upwd,uid);
        return "admin";
    }
}
