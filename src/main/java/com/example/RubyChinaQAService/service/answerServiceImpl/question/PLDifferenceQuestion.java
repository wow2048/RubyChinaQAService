package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.PLRepository;
import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.ProgrammingLanguage;
import com.example.RubyChinaQAService.entity.po.Ruby;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PLDifferenceQuestion implements Question {
    private PLRepository plRepository;

    private RubyRepository rubyRepository;

    private String name;

    @Override
    public String answer() {
        ProgrammingLanguage programmingLanguage = plRepository.findByName(name);
        return name + "和Ruby的差异有：" + System.lineSeparator() + programmingLanguage.getDifference();
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        return ruby.getBlogs().stream().filter(Blog::isExcellent).toList();
    }
}
