package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.entity.po.ApplicationNode;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApplicationBlogQuestion implements Question {

    private ApplicationRepository applicationRepository;

    private String name;

    private String type;

    @Override
    public String answer() {
        return String.format("关于这个问题推荐以下博客%s", System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        ApplicationNode application = applicationRepository.findByName(name);
        List<Blog> result = Lists.newArrayList();
        result.addAll(application.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type)).toList());
        if (result.size() < 20) {
            result.addAll(application.getBlogs().stream().filter(Blog::isExcellent).toList());
        }
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(application.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
