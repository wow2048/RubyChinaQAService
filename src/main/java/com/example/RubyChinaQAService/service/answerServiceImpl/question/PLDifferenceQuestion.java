package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.PLRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.ProgrammingLanguage;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class PLDifferenceQuestion implements Question {
    private PLRepository plRepository;

    @Setter
    private String name;

    @Override
    public String answer() {
        ProgrammingLanguage programmingLanguage = plRepository.findByName(name);
        return name + "和Ruby的差异有：" + System.lineSeparator() + programmingLanguage.getDifference();
    }

    @Override
    public List<Blog> recommend() {
        return Lists.newArrayList();
    }
}
