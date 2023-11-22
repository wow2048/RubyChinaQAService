package com.example.RubyChinaQAService.service.answerServiceImpl;

import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.entity.vo.AnswerVO;
import com.example.RubyChinaQAService.service.AnswerService;
import com.example.RubyChinaQAService.service.answerServiceImpl.classifier.QuestionClassifier;
import com.example.RubyChinaQAService.service.answerServiceImpl.classifier.RubyClassifier;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    @Qualifier("question_classifier")
    List<QuestionClassifier> classifiers;

    @Autowired
    RubyClassifier rubyClassifier;

    @Override
    public List<AnswerVO> answer(String question) {
        List<Question> questionList = classify(question);
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

    private List<Question> classify(String sentence) {
        String[] questions = sentence.split("[,.?;，。？；]");
        List<Question> questionList = Lists.newArrayList();
        Arrays.stream(questions).forEach(each -> {
            boolean flag = false;
            for (QuestionClassifier qc : classifiers) {
                flag = flag || qc.addQuestion(each, questionList);
            }
            if (!flag) {
                rubyClassifier.addQuestion(each, questionList);
            }
        });
        return questionList;
    }
}
