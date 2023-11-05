package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.entity.po.ApplicationNode;
import com.example.RubyChinaQAService.entity.po.Blog;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApplicationDescriptionQuestion implements Question {
    private ApplicationRepository applicationRepository;

    private String name;

    @Override
    public String answer() {
        ApplicationNode applicationNode = applicationRepository.findByName(name);
        return applicationNode.getDescription();
    }

    @Override
    public List<Blog> recommend() {
        ApplicationNode applicationNode = applicationRepository.findByName(name);
        return applicationNode.getBlogs().stream().filter(Blog::isExcellent).toList();
    }
}
