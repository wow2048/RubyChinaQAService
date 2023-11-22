package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.entity.po.ApplicationNode;
import com.example.RubyChinaQAService.entity.po.Blog;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApplicationBlogQuestion implements Question {

    private ApplicationRepository applicationRepository;

    private String name;

    private String type;

    @Override
    public String answer() {
        return String.format("%s的相关%s博客有%s", name, type, System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        ApplicationNode application = applicationRepository.findByName(name);
        return application.getBlogs().stream()
                .filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent())
                .toList();
    }
}
