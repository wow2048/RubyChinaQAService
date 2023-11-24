package com.example.RubyChinaQAService.service;

import com.example.RubyChinaQAService.entity.vo.BlogVO;

import java.util.List;

public interface SearchService {
    List<BlogVO> search(String input);
}
