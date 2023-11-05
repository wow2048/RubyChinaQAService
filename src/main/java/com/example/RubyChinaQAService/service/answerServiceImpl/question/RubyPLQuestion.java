package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.ProgrammingLanguage;
import com.example.RubyChinaQAService.entity.po.Ruby;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RubyPLQuestion implements Question{

    private RubyRepository rubyRepository;

    @Override
    public String answer() {
        StringBuilder sb = new StringBuilder();
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<ProgrammingLanguage> similarLanguage = ruby.getSimilarLanguage();
        similarLanguage.forEach(each -> {
            sb.append(each.getName()).append(": ").append(each.getDescription()).append(System.lineSeparator());
        });
        return sb.toString();
    }

    @Override
    public List<Blog> recommend() {
        return Lists.newArrayList();
    }
}
