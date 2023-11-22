package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.PLRepository;
import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLConsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLDifferenceQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLProsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("question_classifier")
public class PLClassifier extends QuestionClassifier {

    private static final List<String> PL_DICT = Lists.newArrayList("Java", "C++", "Python");

    private static final List<String> DIFFERENCE_DICT = Lists.newArrayList("差异", "不同点", "不同");

    @Autowired
    private RubyRepository rubyRepository;

    @Autowired
    private PLRepository plRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        List<String> plEntities = checkEntity(question, PL_DICT);
        if (CollectionUtils.isEmpty(plEntities)) {
            return false;
        }

        if (checkQuestion(question, PROS_DICT)) {
            plEntities.forEach(each -> {
                questionList.add(new PLProsQuestion(plRepository, each));
            });
        }
        if (checkQuestion(question, CONS_DICT)) {
            plEntities.forEach(each -> {
                questionList.add(new PLConsQuestion(plRepository, each));
            });
        }
        if (checkQuestion(question, DIFFERENCE_DICT)) {
            plEntities.forEach(each -> {
                questionList.add(new PLDifferenceQuestion(plRepository, rubyRepository, each));
            });
        }

        return true;
    }
}
