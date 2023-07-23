package com.test.SpringQuizApp.controller;

import com.test.SpringQuizApp.dto.Question;
import com.test.SpringQuizApp.SpringQuizAppApplication;
import com.test.SpringQuizApp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    Logger logger = LoggerFactory.getLogger(SpringQuizAppApplication.class);
    @Autowired
    public QuestionService questionService;
    @GetMapping("getQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("getQuestionByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addNewQuestion(question);

    }

    @DeleteMapping("deleteQuestion")
    public ResponseEntity<String> deleteQuestion(@RequestBody Question question){
        return questionService.deleteQuestion(question.getId());
    }

}
