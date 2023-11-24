package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ToolRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Tool;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ToolDescriptionQuestion implements Question{

    private ToolRepository toolRepository;

    private String name;

    @Override
    public String answer() {
        Tool tool = toolRepository.findByName(name);
        return tool.getDescription();
    }

    @Override
    public List<Blog> recommend() {
        Tool tool = toolRepository.findByName(name);
        List<Blog> result = Lists.newArrayList();
        result.addAll(tool.getBlogs().stream().filter(Blog::isExcellent).toList());
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(tool.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
