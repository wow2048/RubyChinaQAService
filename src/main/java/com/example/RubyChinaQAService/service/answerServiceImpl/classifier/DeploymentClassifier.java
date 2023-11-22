package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.DeploymentRepository;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.DeployBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("question_classifier")
public class DeploymentClassifier extends QuestionClassifier {

    private static final List<String> DEPLOYMENT_DICT = Lists.newArrayList("部署");

    @Autowired
    private DeploymentRepository deploymentRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        List<String> deploymentEntity = checkEntity(question, DEPLOYMENT_DICT);
        if (CollectionUtils.isEmpty(deploymentEntity)) {
            return false;
        }

        if (checkQuestion(question, BLOG_DICT)) {
            boolean hasType = false;
            if (checkQuestion(question, LEARN_DICT)) {
                hasType = true;
                questionList.add(new DeployBlogQuestion(deploymentRepository, "learning"));
            }
            if (checkQuestion(question, RESOURCE_DICT)) {
                hasType = true;
                questionList.add(new DeployBlogQuestion(deploymentRepository, "resource"));
            }
            if (checkQuestion(question, CASE_DICT)) {
                hasType = true;
                questionList.add(new DeployBlogQuestion(deploymentRepository, "case"));
            }
            if (!hasType) {
                questionList.add(new DeployBlogQuestion(deploymentRepository, ""));
            }
        }
        return true;

    }
}
