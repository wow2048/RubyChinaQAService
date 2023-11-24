package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.DeploymentRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Deployment;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeployBlogQuestion implements Question {

    private DeploymentRepository deploymentRepository;

    private String type;

    @Override
    public String answer() {
        return String.format("关于这个问题推荐以下博客%s", System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        Deployment deployment = deploymentRepository.findByName("部署");
        List<Blog> result = Lists.newArrayList();
        result.addAll(deployment.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent()).toList());
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(deployment.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
