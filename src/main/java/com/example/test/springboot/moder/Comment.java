package com.example.test.springboot.moder;

import lombok.Data;

@Data
public class Comment {
    private Integer parentId;
    private Integer commentator;
    private long gmt_create;
    private String content;
    private String questionTitle;
    private String ico;
    private String name;

}
