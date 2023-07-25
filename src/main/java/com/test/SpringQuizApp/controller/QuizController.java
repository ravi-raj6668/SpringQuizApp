package com.test.SpringQuizApp.controller;

import com.test.SpringQuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    public QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title) {
        return quizService.createQuizApp(category, title);
    }
}
