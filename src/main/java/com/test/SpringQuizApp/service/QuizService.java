package com.test.SpringQuizApp.service;

import com.test.SpringQuizApp.Doa.QuestionDao;
import com.test.SpringQuizApp.Doa.QuizDao;
import com.test.SpringQuizApp.dto.Question;
import com.test.SpringQuizApp.dto.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    public QuizDao quizDao;

    @Autowired
    public QuestionDao questionDao;

    public ResponseEntity<String> createQuizApp(String category, String title) {
        try {
            List<Question> question = questionDao.findQuestionByCategory(category);
            Quiz quiz = new Quiz();
            quiz.setQuiz_title(title);
            quiz.setQuestions(question);
            quizDao.save(quiz);
            return new ResponseEntity<>("Success : New Quiz is created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
