package com.example.test.springboot.dto;

import com.example.test.springboot.moder.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer parentId;
    private Integer commentator;
    private long gmt_create;
    private String content;
    private String questionTitle;
    private String name;
    User user;
}
