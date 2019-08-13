package com.example.test.springboot.moder;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String tag;
    private String description;
    private Integer creator;
    private Long CreationTime;
}
