package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.FeatureRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Feature;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class FeatureBlogQuestion implements Question{

    private FeatureRepository featureRepository;

    @Setter
    private String name;

    @Setter
    private String type;

    @Override
    public String answer() {
        return String.format("%s 的相关%s博客有：%s", name, type, System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        Feature feature = featureRepository.findByName(name);
        return feature.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type)).toList();

    }
}
