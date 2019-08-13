package com.example.test.springboot.controller;

import com.example.test.springboot.mapper.LogonMapper;
import com.example.test.springboot.moder.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private LogonMapper logonMapper;

    @PostMapping("/updata/{id}")
    public String Updata(
            @RequestParam("uname") String uname,
            @RequestParam("upwd") String upwd,
            @PathVariable(name = "id") Integer id,
            Model model) {
        System.out.println(id);
        logonMapper.update(uname, upwd,id);
        return "redirect:index";
    }


    @RequestMapping("/Delete")
    public String Delete(User user){
        logonMapper.delete(user.getId());
        return "redirect:index";
    }

}
