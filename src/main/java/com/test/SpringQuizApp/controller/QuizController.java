package com.test.SpringQuizApp.controller;

import com.test.SpringQuizApp.dto.Question;
import com.test.SpringQuizApp.dto.QuestionWrapper;
import com.test.SpringQuizApp.dto.Quiz;
import com.test.SpringQuizApp.dto.QuizResponse;
import com.test.SpringQuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    public QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title) {
        return quizService.createQuizApp(category, title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuizQuestionsFromDB(id);
    }

    @PostMapping("score/{id}")
    public ResponseEntity<String> getQuizScore(@PathVariable int id, @RequestBody List<QuizResponse> responses){
            return quizService.calculateResult(id,responses);
    }
}
