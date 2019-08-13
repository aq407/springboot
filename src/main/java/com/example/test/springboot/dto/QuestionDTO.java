package com.example.test.springboot.dto;

import com.example.test.springboot.moder.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String tag;
    private String description;
    private Integer creator;
    private Long CreationTime;
    private User user;
}
