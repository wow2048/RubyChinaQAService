package com.example.RubyChinaQAService.controller;

import com.example.RubyChinaQAService.entity.OperationRst;
import com.example.RubyChinaQAService.entity.vo.BlogVO;
import com.example.RubyChinaQAService.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public OperationRst search(@RequestParam("input") String input) {
        List<BlogVO> result = searchService.search(input);
        if (CollectionUtils.isEmpty(result)) {
            return OperationRst.buildFail("没有相匹配的搜索结果");
        }
        return OperationRst.buildSuccess(result);
    }
}
