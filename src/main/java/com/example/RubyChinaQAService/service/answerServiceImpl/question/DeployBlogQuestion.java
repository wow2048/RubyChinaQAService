package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.DeploymentRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Deployment;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeployBlogQuestion implements Question {

    private DeploymentRepository deploymentRepository;

    private String type;

    @Override
    public String answer() {
        return String.format("Ruby部署相关%s的博客有%s", type, System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        Deployment deployment = deploymentRepository.findByName("部署");
        return deployment.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent()).toList();
    }
}
