package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.FeatureRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Feature;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FeatureBlogQuestion implements Question {

    private FeatureRepository featureRepository;

    private String name;

    private String type;

    @Override
    public String answer() {
        return String.format("关于这个问题推荐以下博客%s", System.lineSeparator());
    }

    @Override
    public List<Blog> recommend() {
        Feature feature = featureRepository.findByName(name);
        List<Blog> result = Lists.newArrayList();
        result.addAll(feature.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type) || each.isExcellent()).toList());
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(feature.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
