package com.test.SpringQuizApp.service;

import com.test.SpringQuizApp.Doa.QuestionDao;
import com.test.SpringQuizApp.dto.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    public QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("New Question Inserted Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while inserting data", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            boolean idPresent = questionDao.existsById(id);
            if (idPresent) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Question is deleted with id : " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Question with id : " + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Question with id : " + " not found", HttpStatus.BAD_REQUEST);
        }
    }
}
