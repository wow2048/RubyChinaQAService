package com.example.RubyChinaQAService.controller;

import com.example.RubyChinaQAService.entity.OperationRst;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/get")
    public OperationRst getBlog(@RequestParam("title") String title) {
        Optional<Blog> blog = blogService.getBlog(title);
        return blog.map(OperationRst::buildSuccess)
                .orElseGet(() -> OperationRst.buildFail("这篇文章似乎不存在。"));
    }

}
