package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.entity.po.Blog;

import java.util.List;

public interface Question {
    String answer();

    List<Blog> recommend();
}
