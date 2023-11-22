package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Ruby;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RubyProsQuestion implements Question {

    private RubyRepository rubyRepository;

    @Override
    public String answer() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        return "Ruby语言的技术优点有" + System.lineSeparator() + ruby.getPros();
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        return ruby.getBlogs().stream().filter(Blog::isExcellent).toList();
    }
}
