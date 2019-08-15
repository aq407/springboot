package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/*jkjkjkj*/
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
}
