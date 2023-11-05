package com.example.RubyChinaQAService.service.answerServiceImpl;

import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.vo.AnswerVO;
import com.example.RubyChinaQAService.service.AnswerService;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private QuestionClassifier classifier;

    @Override
    public List<AnswerVO> answer(String question) {
        List<Question> questionList = classifier.classify(question);
        List<AnswerVO> res = Lists.newArrayList();
        questionList.forEach(each -> {
            AnswerVO answerVO = new AnswerVO();
            String answer = each.answer();
            answerVO.setAnswer(answer);
            List<Blog> blogs = each.recommend();
            if (!CollectionUtils.isEmpty(blogs)) {
                answerVO.setBlogs(blogs);
            }
            res.add(answerVO);
        });
        return res;
    }
}
