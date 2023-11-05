package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Feature;
import com.example.RubyChinaQAService.entity.po.Ruby;
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
        List<Blog> blogs = ruby.getBlogs();
        return blogs.stream().filter(each -> each.getTitle().contains("特性")).toList();
    }
}
