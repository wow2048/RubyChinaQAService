package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Ruby;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class RubyBlogQuestion implements Question{

    private RubyRepository rubyRepository;

    @Setter
    private String type;

    @Override
    public String answer() {
        return "Ruby相关的" + type + "博客有" + System.lineSeparator();
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        return ruby.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent()).toList();
    }
}
