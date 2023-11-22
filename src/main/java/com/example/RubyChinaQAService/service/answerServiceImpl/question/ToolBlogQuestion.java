package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.ToolRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Tool;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ToolBlogQuestion implements Question{

    private ToolRepository toolRepository;

    private String name;

    private String type;

    @Override
    public String answer() {
        return String.format("%s的相关%s博客有%s", name, type, System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        Tool tool = toolRepository.findByName(name);
        return tool.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent()).toList();
    }
}
