package com.test.SpringQuizApp.service;

import com.test.SpringQuizApp.Doa.QuestionDao;
import com.test.SpringQuizApp.Doa.QuizDao;
import com.test.SpringQuizApp.dto.Question;
import com.test.SpringQuizApp.dto.QuestionWrapper;
import com.test.SpringQuizApp.dto.Quiz;
import com.test.SpringQuizApp.dto.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsFromDB(int id) {
        try {
            if (quizDao.existsById(id)) {
                Optional<Quiz> quizData = quizDao.findById(id);
                List<Question> questionFromDB = quizData.get().getQuestions();


                List<QuestionWrapper> questionForUser = new ArrayList<>();
                for (Question q : questionFromDB) {
                    QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestion_title(),
                            q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                    questionForUser.add(questionWrapper);
                }
                return new ResponseEntity<>(questionForUser, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> calculateResult(int id, List<QuizResponse> responses) {
        try {
            Quiz quiz = new Quiz();
            if (quizDao.existsById(id)) {
                quiz = quizDao.findById(id).get();
            }
            List<Question> questionList = quiz.getQuestions();
            int rightAnswer = 0;
            int count = 0;
            for (QuizResponse response : responses) {
                if (response.getResponse().equals(questionList.get(count).getRight_answer())) {
                    rightAnswer++;
                }
                count++;
            }
            return new ResponseEntity<>("Your score : " + rightAnswer, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while fetching quiz score", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
