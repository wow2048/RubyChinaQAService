package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.FeatureRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.FeatureBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.FeatureDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("question_classifier")
public class FeatureClassifier extends QuestionClassifier {

    private static final List<String> FEATURE_DICT = Lists.newArrayList("面向对象", "动态类型", "垃圾回收机制", "模块化", "元编程", "迭代器"
            , "异常处理机制", "多线程机制", "拷贝机制", "闭包", "函数式编程", "钩子方法", "方法查找机制", "OpenStruct", "作用域");

    @Autowired
    FeatureRepository featureRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        List<String> featureEntities = checkEntity(question, FEATURE_DICT);
        if (CollectionUtils.isEmpty(featureEntities)) {
            return false;
        }

        if (checkQuestion(question, DESCRIPTION_DICT)) {
            featureEntities.forEach(each -> {
                questionList.add(new FeatureDescriptionQuestion(featureRepository, each));
            });
        }
        if (checkQuestion(question, BLOG_DICT)) {
            boolean hasType = false;
            if (checkQuestion(question, LEARN_DICT)) {
                hasType = true;
                featureEntities.forEach(each -> {
                    questionList.add(new FeatureBlogQuestion(featureRepository, each, "study"));
                });
            }
            if (checkQuestion(question, RESOURCE_DICT)) {
                hasType = true;
                featureEntities.forEach(each -> {
                    questionList.add(new FeatureBlogQuestion(featureRepository, each, "resource"));
                });
            }
            if (checkQuestion(question, CASE_DICT)) {
                hasType = true;
                featureEntities.forEach(each -> {
                    questionList.add(new FeatureBlogQuestion(featureRepository, each, "case"));
                });
            }
            if (!hasType) {
                featureEntities.forEach(each -> {
                    questionList.add(new FeatureBlogQuestion(featureRepository, each, ""));
                });
            }
        }
        return true;
    }

    @Override
    public List<Blog> checkDict(String input) {
        List<Blog> blogs = Lists.newArrayList();
        FEATURE_DICT.forEach(each -> {
            if (input.contains(each)) {
                blogs.addAll(featureRepository.findByName(each).getBlogs());
            }
        });
        return blogs;
    }
}
