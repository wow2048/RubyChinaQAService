package com.example.RubyChinaQAService.service.blogServiceImpl;

import com.example.RubyChinaQAService.dao.BlogRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Optional<Blog> getBlog(String title) {
        List<Blog> blogs = blogRepository.findByTitle(title);
        if (CollectionUtils.isEmpty(blogs)) {
            return Optional.empty();
        }
        return Optional.of(blogs.get(0));
    }
}
