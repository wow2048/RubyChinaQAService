package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Feature;
import com.example.RubyChinaQAService.entity.po.Ruby;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RubyFeatureQuestion implements Question{

    private RubyRepository rubyRepository;

    @Override
    public String answer() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<Feature> features = ruby.getFeatures();
        String featureNames = features.stream().map(Feature::getName).collect(Collectors.joining(","));
        return "Ruby的特性包括：" + featureNames;
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<Blog> result = Lists.newArrayList();
        ruby.getFeatures().forEach(each -> {
            List<Blog> excellentBlogs = Lists.newArrayList(each.getBlogs().stream().filter(Blog::isExcellent).toList());
            result.addAll(excellentBlogs.subList(0, Math.min(5, excellentBlogs.size())));
        });
        result.addAll(ruby.getBlogs().stream().filter(each -> each.getTitle().contains("特性")).toList());
        return result;
    }
}
