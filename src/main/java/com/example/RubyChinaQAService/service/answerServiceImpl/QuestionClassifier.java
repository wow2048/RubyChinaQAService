package com.example.RubyChinaQAService.service.answerServiceImpl;

import com.example.RubyChinaQAService.dao.ApplicationRepository;
import com.example.RubyChinaQAService.dao.DeploymentRepository;
import com.example.RubyChinaQAService.dao.FeatureRepository;
import com.example.RubyChinaQAService.dao.PLRepository;
import com.example.RubyChinaQAService.dao.RubyRepository;
import com.example.RubyChinaQAService.dao.ToolRepository;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ApplicationBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ApplicationDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.DeployBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.FeatureBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.FeatureDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLConsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLDifferenceQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.PLProsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.Question;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyApplicationQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyConsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyDescriptionQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyFeatureQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyPLQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyProsQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.RubyToolQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ToolBlogQuestion;
import com.example.RubyChinaQAService.service.answerServiceImpl.question.ToolDescriptionQuestion;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class QuestionClassifier {

    private static final List<String> PL_DICT = Lists.newArrayList("Java", "C++", "Python");

    private static final List<String> FEATURE_DICT = Lists.newArrayList("面向对象", "动态类型", "垃圾回收机制", "模块化", "元编程", "迭代器"
            , "异常处理机制", "多线程机制", "拷贝机制", "闭包", "函数式编程", "钩子方法", "方法查找机制", "OpenStruct", "作用域");

    private static final List<String> APPLICATION_DICT = Lists.newArrayList("Cucumber", "Chef", "Capistrano",
            "Goliath", "MRuby", "Puppet", "Ruby on Rails", "Rspec", "Sinatra", "Watir");

    private static final List<String> TOOL_DICT = Lists.newArrayList("RVM", "rbenv", "RubyGems", "Bundler", "Pry",
            "ast-grep", "Backup");

    private static final List<String> DEPLOYMENT_DICT = Lists.newArrayList("部署");

    private static final List<String> DESCRIPTION_DICT = Lists.newArrayList("介绍", "描述", "说", "怎样", "是什么", "怎么样");

    private static final List<String> PROS_DICT = Lists.newArrayList("优点", "好的", "优秀");

    private static final List<String> CONS_DICT = Lists.newArrayList("缺点");

    private static final List<String> DIFFERENCE_DICT = Lists.newArrayList("差异", "不同点", "不同");

    private static final List<String> BLOG_DICT = Lists.newArrayList("博客", "博文", "文章", "教程", "案例");

    private static final List<String> LEARN_DICT = Lists.newArrayList("学习", "教程");

    private static final List<String> RESOURCE_DICT = Lists.newArrayList("资源", "书");

    private static final List<String> CASE_DICT = Lists.newArrayList("案例", "实践");

    private static final List<String> RUBY_FEATURE_DICT = Lists.newArrayList("特性");

    private static final List<String> RUBY_PL_DICT = Lists.newArrayList("类似的语言", "编程语言", "语言");

    private static final List<String> RUBY_APPLICATION_DICT = Lists.newArrayList("应用", "框架", "生态");

    private static final List<String> RUBY_TOOL_DICT = Lists.newArrayList("开发工具");

    private static final List<String> HAS_WORLD = Lists.newArrayList("有", "哪些");

    @Autowired
    private RubyRepository rubyRepository;

    @Autowired
    private PLRepository plRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private DeploymentRepository deploymentRepository;

    public List<Question> classify(String sentence) {
        String[] questions = sentence.split("[,.?;，。？；]");
        List<Question> questionList = Lists.newArrayList();
        for (String question : questions) {
            boolean plFlag = addPLQuestion(question, questionList);
            boolean feaFlag = addFeatureQuestion(question, questionList);
            boolean appFlag = addApplicationQuestion(question, questionList);
            boolean deFlag = addDeploymentQuestion(question, questionList);
            boolean toFlag = addToolQuestion(question, questionList);
            if (!(plFlag || feaFlag || appFlag || deFlag || toFlag)) {
                addRubyQuestion(question, questionList);
            }
        }
        return questionList;
    }

    private void addRubyQuestion(String question, List<Question> questionList) {
        if (!(question.contains("Ruby") || question.contains("ruby"))) {
            return;
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
    }

    private boolean addPLQuestion(String question, List<Question> questionList) {
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

    private boolean addFeatureQuestion(String question, List<Question> questionList) {
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
                    questionList.add(new FeatureBlogQuestion(featureRepository, each, "learning"));
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

    private boolean addApplicationQuestion(String question, List<Question> questionList) {
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

    private boolean addToolQuestion(String question, List<Question> questionList) {
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
                    questionList.add(new ToolBlogQuestion(toolRepository, each, "learning"));
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

    private boolean addDeploymentQuestion(String question, List<Question> questionList) {
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


    private List<String> checkEntity(String question, List<String> dict) {
        List<String> entities = Lists.newArrayList();
        dict.forEach(each -> {
            if (question.contains(each)) {
                entities.add(each);
            }
        });
        return entities;
    }

    private boolean checkQuestion(String question, List<String> dict) {
        for (String word : dict) {
            if (question.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
