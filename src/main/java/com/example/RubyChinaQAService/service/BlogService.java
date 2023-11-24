package com.example.RubyChinaQAService.service;

import com.example.RubyChinaQAService.entity.po.Blog;

import java.util.Optional;

public interface BlogService {
    Optional<Blog> getBlog(String title);
}
