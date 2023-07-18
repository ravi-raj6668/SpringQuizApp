package com.test.SpringQuizApp.controller;

import com.test.SpringQuizApp.dto.Question;
import com.test.SpringQuizApp.SpringQuizAppApplication;
import com.test.SpringQuizApp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    Logger logger = LoggerFactory.getLogger(SpringQuizAppApplication.class);
    @Autowired
    public QuestionService questionService;
    @GetMapping("getQuestion")
    public List<Question> getAllQuestions(){
        logger.info("Inside get All Questions");
        List<Question> questionList = questionService.getAllQuestions();
        logger.info("Getting result : " + questionList.toString());
        return questionList;
    }

    @GetMapping("getQuestionByCategory/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
        logger.info("Inside get question by category");
        List<Question> questionByCategory =  questionService.getQuestionByCategory(category);
        logger.info("Getting result from method get question by category : " + questionByCategory.toString());
        return questionByCategory;
    }

}
