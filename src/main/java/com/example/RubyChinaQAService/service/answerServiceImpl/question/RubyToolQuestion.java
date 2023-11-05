package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Ruby;
import com.example.RubyChinaQAService.entity.po.Tool;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RubyToolQuestion implements Question{

    private RubyRepository rubyRepository;

    @Override
    public String answer() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<Tool> tools = ruby.getTools();
        StringBuilder sb = new StringBuilder();
        tools.forEach(each -> {
            sb.append(each.getName()).append(":").append(each.getDescription()).append(System.lineSeparator());
        });
        return sb.toString();
    }

    @Override
    public List<Blog> recommend() {
        return Lists.newArrayList();
    }
}
