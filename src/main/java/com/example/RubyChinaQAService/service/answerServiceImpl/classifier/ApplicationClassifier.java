package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ApplicationBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ApplicationDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("question_classifier")
public class ApplicationClassifier extends QuestionClassifier {

    private static final List<String> APPLICATION_DICT = Lists.newArrayList("Cucumber", "Chef", "Capistrano",
            "Goliath", "MRuby", "Puppet", "Ruby on Rails", "Rspec", "Sinatra", "Watir");

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        {
            List<String> applicationEntities = checkEntity(question, APPLICATION_DICT);
            if (CollectionUtils.isEmpty(applicationEntities)) {
                return false;
            }

            if (checkQuestion(question, DESCRIPTION_DICT)) {
                applicationEntities.forEach(each -> {
                    questionList.add(new ApplicationDescriptionQuestion(applicationRepository, each));
                });
            }
            if (checkQuestion(question, BLOG_DICT)) {
                boolean hasType = false;
                if (checkQuestion(question, LEARN_DICT)) {
                    hasType = true;
                    applicationEntities.forEach(each -> {
                        questionList.add(new ApplicationBlogQuestion(applicationRepository, each, "learning"));
                    });
                }
                if (checkQuestion(question, RESOURCE_DICT)) {
                    hasType = true;
                    applicationEntities.forEach(each -> {
                        questionList.add(new ApplicationBlogQuestion(applicationRepository, each, "resource"));
                    });
                }
                if (checkQuestion(question, CASE_DICT)) {
                    hasType = true;
                    applicationEntities.forEach(each -> {
                        questionList.add(new ApplicationBlogQuestion(applicationRepository, each, "case"));
                    });
                }
                if (!hasType) {
                    applicationEntities.forEach(each -> {
                        questionList.add(new ApplicationBlogQuestion(applicationRepository, each, ""));
                    });
                }
            }
            return true;
        }
    }
}
