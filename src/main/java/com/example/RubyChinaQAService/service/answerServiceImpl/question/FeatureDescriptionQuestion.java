package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.FeatureRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Feature;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class FeatureDescriptionQuestion implements Question {

    private FeatureRepository featureRepository;

    @Setter
    private String name;

    @Override
    public String answer() {
        Feature feature = featureRepository.findByName(name);
        return feature.getDescription();
    }

    @Override
    public List<Blog> recommend() {
        Feature feature = featureRepository.findByName(name);
        List<Blog> result = Lists.newArrayList();
        result.addAll(feature.getBlogs().stream().filter(Blog::isExcellent).toList());
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(feature.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
