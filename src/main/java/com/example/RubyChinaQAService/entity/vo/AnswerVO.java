package com.example.RubyChinaQAService.entity.vo;

import com.example.RubyChinaQAService.entity.po.Blog;
import lombok.Data;

import java.util.List;

@Data
public class AnswerVO {
    private String answer;

    private List<Blog> blogs;
}
