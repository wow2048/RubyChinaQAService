package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyApplicationQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyConsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyFeatureQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyPLQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyProsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyToolQuestion;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RubyClassifier extends QuestionClassifier {

    private static final List<String> RUBY_FEATURE_DICT = Lists.newArrayList("特性");

    private static final List<String> RUBY_PL_DICT = Lists.newArrayList("类似的语言", "编程语言", "语言");

    private static final List<String> RUBY_APPLICATION_DICT = Lists.newArrayList("应用", "框架", "生态");

    private static final List<String> RUBY_TOOL_DICT = Lists.newArrayList("开发工具");

    private static final List<String> HAS_WORLD = Lists.newArrayList("有", "哪些");

    @Autowired
    private RubyRepository rubyRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        if (!(question.contains("Ruby") || question.contains("ruby"))) {
            return true;
        }

        if (checkQuestion(question, DESCRIPTION_DICT)) {
            questionList.add(new RubyDescriptionQuestion(rubyRepository));
        }
        if (checkQuestion(question, PROS_DICT)) {
            questionList.add(new RubyProsQuestion(rubyRepository));
        }
        if (checkQuestion(question, CONS_DICT)) {
            questionList.add(new RubyConsQuestion(rubyRepository));
        }
        if (checkQuestion(question, HAS_WORLD)) {
            if (checkQuestion(question, RUBY_FEATURE_DICT)) {
                questionList.add(new RubyFeatureQuestion(rubyRepository));
            }
            if (checkQuestion(question, RUBY_APPLICATION_DICT)) {
                questionList.add(new RubyApplicationQuestion(rubyRepository));
            }
            if (checkQuestion(question, RUBY_PL_DICT)) {
                questionList.add(new RubyPLQuestion(rubyRepository));
            }
            if (checkQuestion(question, RUBY_TOOL_DICT)) {
                questionList.add(new RubyToolQuestion(rubyRepository));
            }
        }
        if (checkQuestion(question, BLOG_DICT)) {
            boolean hasType = false;
            if (checkQuestion(question, LEARN_DICT)) {
                hasType = true;
                questionList.add(new RubyBlogQuestion(rubyRepository, "learning"));
            }
            if (checkQuestion(question, RESOURCE_DICT)) {
                hasType = true;
                questionList.add(new RubyBlogQuestion(rubyRepository, "resource"));
            }
            if (checkQuestion(question, CASE_DICT)) {
                hasType = true;
                questionList.add(new RubyBlogQuestion(rubyRepository, "case"));
            }
            if (!hasType) {
                questionList.add(new RubyBlogQuestion(rubyRepository, ""));
            }
        }
        return true;
    }
}
