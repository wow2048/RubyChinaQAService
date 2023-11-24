package com.example.RubyChinaQAService.service.answerServiceImpl.question;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.po.Ruby;
import com.google.common.collect.Lists;
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
        return "关于这个问题推荐以下博客" + System.lineSeparator();
    }

    @Override
    public List<Blog> recommend() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        List<Blog> result = Lists.newArrayList();
        result.addAll(ruby.getBlogs().stream().filter(each -> each.getType().equalsIgnoreCase(type)).toList());
        if (result.size() < 20) {
            result.addAll(ruby.getBlogs().stream().filter(Blog::isExcellent).toList());
        }
        if (result.size() < 20) {
            List<Blog> normalBlogs =
                    Lists.newArrayList(ruby.getBlogs().stream().filter(each -> !each.isExcellent()).toList());
            result.addAll(normalBlogs.subList(0, Math.min(20 - result.size(), normalBlogs.size())));
        }
        return result;
    }
}
