package com.example.test.springboot.controller;

import com.example.test.springboot.dto.QuestionDTO;
import com.example.test.springboot.mapper.CommentMapper;
import com.example.test.springboot.moder.Comment;
import com.example.test.springboot.moder.User;
import com.example.test.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService  questionService;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);
        List <Comment> comments = commentMapper.getReply();
        model.addAttribute("comment",comments);
        model.addAttribute("ids",id);
        return "question";
    }

    @PostMapping("/{id}")
    public String comment(@PathVariable(name = "id") Integer id,
                          @RequestParam (name = "questionId") Integer questionId,
                          @RequestParam (name = "content") String content,
                          @RequestParam (name = "questionTitle") String questionTitle,
                          Model model,
                          HttpServletRequest request){
        //Object user = request.getSession().getAttribute("user");
        String question = "/question/";
        String num = id.toString();
        String html = question + num;

        System.out.println(content);//回复内容 content
        System.out.println(questionId);//回帖id parent_id
        User user = (User) request.getSession().getAttribute("user");

            //回复人id commentator
            Long time = System.currentTimeMillis();//回复时间 gmt_modified
            System.out.println(questionTitle);//回复标题
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setParentId(questionId);
            comment.setCommentator(user.getId());
            comment.setQuestionTitle(questionTitle);
            comment.setGmt_create(time);
            comment.setIco(user.getIconurl());
            comment.setName(user.getUsername());
            commentMapper.reply(comment);
            return "redirect:"+html;
    }


}
