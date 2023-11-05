package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ToolRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Tool;
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
        return tool.getBlogs().stream().filter(Blog::isExcellent).toList();
    }
}
