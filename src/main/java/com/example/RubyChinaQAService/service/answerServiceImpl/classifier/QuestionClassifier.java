package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class QuestionClassifier {

    protected static final List<String> DESCRIPTION_DICT = Lists.newArrayList("介绍", "描述", "说", "怎样", "是什么", "怎么样");

    protected static final List<String> PROS_DICT = Lists.newArrayList("优点", "好的", "优秀");

    protected static final List<String> CONS_DICT = Lists.newArrayList("缺点");

    protected static final List<String> BLOG_DICT = Lists.newArrayList("博客", "博文", "文章", "教程", "案例");

    protected static final List<String> LEARN_DICT = Lists.newArrayList("学习", "教程");

    protected static final List<String> RESOURCE_DICT = Lists.newArrayList("资源", "书");

    protected static final List<String> CASE_DICT = Lists.newArrayList("案例", "实践");

    public abstract boolean addQuestion(String question, List<Question> questionList);

    protected List<String> checkEntity(String question, List<String> dict) {
        List<String> entities = Lists.newArrayList();
        dict.forEach(each -> {
            if (question.contains(each)) {
                entities.add(each);
            }
        });
        return entities;
    }

    protected boolean checkQuestion(String question, List<String> dict) {
        for (String word : dict) {
            if (question.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
