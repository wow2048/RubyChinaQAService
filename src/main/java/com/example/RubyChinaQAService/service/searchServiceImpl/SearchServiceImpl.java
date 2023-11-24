package com.example.RubyChinaQAService.service.searchServiceImpl;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.vo.BlogVO;
import com.example.RubyChinaQAService.service.SearchService;
import com.example.RubyChinaQAService.service.answerServiceImpl.classifier.QuestionClassifier;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    @Qualifier("question_classifier")
    List<QuestionClassifier> classifiers;

    @Autowired
    private RubyRepository rubyRepository;

    @Override
    public List<BlogVO> search(String input) {
        List<Blog> result = Lists.newArrayList();

        classifiers.forEach(each -> result.addAll(each.checkDict(input)));
        if (input.contains("Ruby")) {
            result.addAll(rubyRepository.findByName("Ruby").getBlogs());
        }

        String[] keys = input.split(" ");
        List<BlogVO> blogVOList = result.stream().map(each -> {
            int score = 0;
            for (String key : keys) {
                if (each.getTitle().contains(key)) {
                    score += 2;
                }
                if (each.isExcellent()) {
                    score += 1;
                }
            }
            return new BlogVO(each, score);
        }).sorted((o1, o2) -> o2.getSort() - o1.getSort()).toList();
        return blogVOList;
    }
}

