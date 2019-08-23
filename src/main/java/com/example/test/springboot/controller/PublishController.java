package com.example.test.springboot.controller;

import com.example.test.springboot.dto.QuestionDTO;
import com.example.test.springboot.moder.Question;
import com.example.test.springboot.moder.User;
import com.example.test.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("question/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {


        QuestionDTO questions = questionService.getById(id);
        model.addAttribute("title", questions.getTitle());
        model.addAttribute("description", questions.getDescription());
        model.addAttribute("tag", questions.getTag());
        model.addAttribute("tag", questions.getId());
        return "publish";
    }


    @GetMapping("/publish")
    public String publish() {

        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);

        if (title == "" || description ==""){
            model.addAttribute("error","请先输入问题或者标题");
            return "publish";
        }

        if (user == null) {
            model.addAttribute("error","请先登录");
            return "publish";
        } else {
            System.out.println("执行");
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            questionService.createOrUpdate(question);
            return "redirect:/";
        }
    }
}
