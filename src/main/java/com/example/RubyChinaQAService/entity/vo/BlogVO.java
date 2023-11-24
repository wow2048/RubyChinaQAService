package com.example.RubyChinaQAService.entity.vo;

import com.example.RubyChinaQAService.entity.po.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {

    private Blog blog;

    private int sort;
}
