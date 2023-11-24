package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.entity.po.ApplicationNode;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.google.common.collect.Lists;
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
        List<Blog> result = Lists.newArrayList();
        result.addAll(applicationNode.getBlogs().stream().filter(Blog::isExcellent).toList());

        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(applicationNode.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
