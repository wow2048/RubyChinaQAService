package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Ruby;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RubyApplicationQuestion implements Question {
    private RubyRepository rubyRepository;

    @Override
    public String answer() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        StringBuilder sb = new StringBuilder();
        ruby.getApplications().forEach(each -> {
            sb.append(each.getName()).append(": ").append(each.getDescription()).append(System.lineSeparator());

        });
        return sb.toString();
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<Blog> result = Lists.newArrayList();
        ruby.getApplications().forEach(each -> {
            List<Blog> excellentBlogs = Lists.newArrayList(each.getBlogs().stream().filter(Blog::isExcellent).toList());
            result.addAll(excellentBlogs.subList(0, Math.min(5, excellentBlogs.size())));
        });
        return result;
    }
}
