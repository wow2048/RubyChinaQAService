package com.example.RubyChinaQAService.service.answerServiceImpl.classifier;

import com.example.RubyChinaQAService.dao.ToolRepository;
import com.example.RubyChinaQAService.entity.po.Blog;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ToolBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ToolDescriptionQuestion;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("question_classifier")
public class ToolClassifier extends QuestionClassifier {

    private static final List<String> TOOL_DICT = Lists.newArrayList("RVM", "rbenv", "RubyGems", "Bundler", "Pry",
            "ast-grep", "Backup");

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public boolean addQuestion(String question, List<Question> questionList) {
        List<String> toolEntities = checkEntity(question, TOOL_DICT);
        if (CollectionUtils.isEmpty(toolEntities)) {
            return false;
        }

        if (checkQuestion(question, DESCRIPTION_DICT)) {
            toolEntities.forEach(each -> {
                questionList.add(new ToolDescriptionQuestion(toolRepository, each));
            });
        }
        if (checkQuestion(question, BLOG_DICT)) {
            boolean hasType = false;
            if (checkQuestion(question, LEARN_DICT)) {
                hasType = true;
                toolEntities.forEach(each -> {
                    questionList.add(new ToolBlogQuestion(toolRepository, each, "study"));
                });
            }
            if (checkQuestion(question, RESOURCE_DICT)) {
                hasType = true;
                toolEntities.forEach(each -> {
                    questionList.add(new ToolBlogQuestion(toolRepository, each, "resource"));
                });
            }
            if (checkQuestion(question, CASE_DICT)) {
                hasType = true;
                toolEntities.forEach(each -> {
                    questionList.add(new ToolBlogQuestion(toolRepository, each, "case"));
                });
            }
            if (!hasType) {
                toolEntities.forEach(each -> {
                    questionList.add(new ToolBlogQuestion(toolRepository, each, ""));
                });
            }
        }
        return true;
    }

    @Override
    public List<Blog> checkDict(String input) {
        List<Blog> blogs = Lists.newArrayList();
        TOOL_DICT.forEach(each -> {
            if (input.contains(each)) {
                blogs.addAll(toolRepository.findByName(each).getBlogs());
            }
        });
        return blogs;
    }
}
